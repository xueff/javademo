package vertx;


import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;
import ramdon.RamdonStudy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuefei
 * @Description: 3.7	设备信息上报（携住->碧桂园）
 * @date 2018/10/1213:58
 */
class Main{
    public static void main(String[] args) {
        List<DeviceOnlineStatusModel> list = new ArrayList<DeviceOnlineStatusModel>();
        for (int i=0;i<10;i++) {
            list.add(new DeviceOnlineStatusModel(i,RamdonStudy.getRamdonInt(30)+"AAAAAA"));
            list.add(new DeviceOnlineStatusModel(i,RamdonStudy.getRamdonInt(30)+"CCCCAA"));
        }




    }

}

public class PushDeviceInfosToBGY {
    static Map<String,List<DeviceOnlineStatusModel>> map = new ConcurrentHashMap<>();
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public void send(List<DeviceOnlineStatusModel> list) {
        Set<String> deviceIdFatherSet = new HashSet<>();//同组设备,去最后两位
        boolean hasNewDevice = false;//是否有新设备入网

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPushMsgType() == 1) {  //新设备入网，同组设备入网一起上传,要sleep3秒等待,
                hasNewDevice = true;
                String deviceId = list.get(i).getDevNo();
                String deviceIdFather = deviceId.substring(0, deviceId.length() - 2);//同组设备（只有2位最后不同）
                deviceIdFatherSet.add(deviceIdFather);
                List<DeviceOnlineStatusModel> listCache = map.get(deviceIdFather);

                if (listCache == null) {
                    synchronized (map) {
                        list =  map.get(deviceIdFather);
                        if (list == null) {
                            map.put(deviceIdFather, new ArrayList<DeviceOnlineStatusModel>());
                        }
                    }
                }
                //map.get(deviceIdFather).add(list.get(i));
            } else {
                send(convertToString(null, list.get(i), list.get(i).getPushMsgType()));
            }
        }

        if(hasNewDevice){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Iterator<String> it = deviceIdFatherSet.iterator();
            while (it.hasNext()) {
                String father = it.next();
                List<DeviceOnlineStatusModel> listCache;
                synchronized (map) {
                    listCache = map.get(father);
                    map.remove(father);//清除缓存
                    if (listCache != null && listCache.size() < 1)
                        continue;
                }
                if(listCache != null && listCache.size()>0)
                    send(convertToString(listCache, null, 1));
            }
        }

        System.out.println("map ="+map);
    }


    private String convertToString(List<DeviceOnlineStatusModel> cmdList,  DeviceOnlineStatusModel cmds,Integer type){
        JsonObject json = new JsonObject();
        if(type != null && type == 1){//新设备
            for (int i=0;i<cmdList.size();i++){
                if(!json.containsKey("pluginDevType")){
                    json.put("pluginDevType","axj01")
                            .put("pushMsgType",type)
                            .put("pushCcuid",cmdList.get(i).getPushCcuid())
                            .put("msg",cmdList.get(i).getMsg())
                            .put("childs",new JsonArray());
                }
                JsonObject jsonChild = new JsonObject();
                jsonChild.put("pushMsg", cmdList.get(i).getPushMsg())
                        .put("devNo",cmdList.get(i).getDevNo())
                        .put("status",cmdList.get(i).getStatus());
                json.getJsonArray("childs").add(jsonChild);

            }
        }else{
//            cmds.g
            json.put("pluginDevType","axj01");  //固定值
            //        1-新设备入网:修改
            //        2-删除设备推送,
            //        3-设备状态上传,
            //        4-报警
            //        5-情景面板触发
            //        6-网关升级状态上传

            json.put("pushMsgType",type)  //上报消息类型
                    .put("pushMsg", cmds.getPushMsg())  //上报消息实体
                    .put("pushCcuid",cmds.getPushCcuid())  //网关编号
                    .put("devNo",cmds.getDevNo()) //子设备编号
                    .put("msg",cmds.getMsg()) //警报内容
                    .put("status",cmds.getStatus());  //设备状态

        }
        return json.toString();
    }

    public void send(String json){
        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                if(StringUtils.isNotEmpty(json))
                        System.out.println(json);
            }
        });
    }
}
