//package worktest;
//
//
//import io.vertx.core.json.JsonObject;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// * 分配主机，简单负载均衡
// */
//public class HostDistribution {
//        @Test
//        fun a() {
//            var dcList: MutableLvertist<JsonObject> = mutableListOf()
//            dcList.add(JsonObject().put("ip","192.167.1574.5215").put("HostTCPServerChannel:1820",JsonObject()))
//            dcList.add(JsonObject().put("ip","121.167.1574.5215").put("HostTCPServerChannel:1820",JsonObject()))
//            dcList.add(JsonObject().put("ip","3256.yuu.1574.5215").put("HostTCPServerChannel:1820",JsonObject().put(",","")))
//            dcList.add(JsonObject().put("ip","698.167.1574.5215").put("HostTCPServerChannel:1820",JsonObject()))
//            dcList.add(JsonObject().put("ip","78.167.g.5215").put("HostTCPServerChannel:1820",JsonObject()))
//            dcList.add(JsonObject().put("ip","54.167.1574.v").put("HostTCPServerChannel:1820",JsonObject()))
//            dcList.add(JsonObject().put("ip","34.167.1574.5215").put("HostTCPServerChannel:1820",JsonObject()))
//            dcList.add(JsonObject().put("ip","192.9.1574.5215").put("HostTCPServerChannel:1820",JsonObject()))
//            dcList.add(JsonObject().put("ip","192.9.2.3").put("HostTCPServerChannel:1820",JsonObject().put("000","")))
//            dcList.add(JsonObject().put("ip","192.9.2.4").put("HostTCPServerChannel:1820",JsonObject()))
//            for(i in 1..10000) {
//                val ip = chooseDc(dcList,"jkkjhjkhjk")
//                for(json in dcList){
//                    if(json.getString("ip").equals(ip))
//                        json.getJsonObject("HostTCPServerChannel:1820").put(UUID.randomUUID().toString(),"")
//                }
//
//                if(i.mod(500).equals(0))
//                    dcList.add(JsonObject().put("ip",UUID.randomUUID().toString()).put("HostTCPServerChannel:1820",JsonObject()))
//
//                if(StringUtils.isEmpty(ip)) continue
//
//
//                var addNum = RamdonStudy.getRamdonInt(5)
//                var addDc = RamdonStudy.getRamdonInt(9);
//                var j = dcList.get(addDc).getJsonObject("HostTCPServerChannel:1820")
//                for(x in 1..addNum) {  //随机手动增加主机
//                    if(RamdonStudy.getRamdonInt(10)>=9)
//                        j.put(UUID.randomUUID().toString(),"")
//                }
//                println(dcList.forEach(
//                        {
//                                print("  "+it.getJsonObject("HostTCPServerChannel:1820").size())
//                        }
//                ))
//
//            }
//            println(dcList)
//        }
//
//        private fun  chooseDc(dcList:MutableList<JsonObject>,hostmac: String?):String{
//            if(dcList.size ==1) return dcList.get(0).getString("ip")
//            var ipuse = ""
//            var sortMap = TreeMap<String,Int>()
//            for(json in dcList){
//                val ip = json.getString("ip")
//                val hosts = json.getJsonObject("HostTCPServerChannel:1820")
//                hosts.forEach({
//                if(it.key.equals(hostmac)) {
//                    ipuse = ip
//                    return@forEach
//                }
//            })
//
//                if(ipuse != "") return ipuse
//
//                val count = hosts.size()
//                //过1000过滤
//                if(count <1000)
//                    sortMap[ip] = count
//            }
//
//            if(sortMap.size==0) throw RuntimeException("dc不存在，或者没有可分配dc")
//
//            //dc扩容时，新dc
//            var maxip = ""
//            var max = Int.MIN_VALUE
//            var mixip = ""
//            var mix = Int.MAX_VALUE
//
//            var selectIp = ""
//            for(item in sortMap){
//                if(item.value>max){
//                    max = item.value
//                    maxip = item.key
//                }
//                if(item.value<mix){
//                    mix = item.value
//                    mixip = item.key
//                }
//                // 正常分配，增长满30，换dc,,防止手动绑定，影响
//                if((item.value==0 || item.value.mod(30) != 0) && selectIp.equals("")){
//
//                    selectIp =  item.key
//                }
//            }
//
//            if(max-30>mix) return mixip;
//            if(!selectIp.equals("")) return selectIp;
//            return sortMap.firstKey();
//        }
//    }
//
