
package com.dayee.wintalent.service.position;

public interface ChannelPositionService {

    // 5. 增加并发布职位，如果有职位信息错误不能发布的情况，可以用该方法修改并发布职位
    public String addAndModifyPost(String corpCode,
                                   String userName,
                                   String password,
                                   String channelPostXml);

    // 6. 操作职位，刷新、暂停等操作使用
    public String opPost(String corpCode,
                         String userName,
                         String password,
                         int opType,
                         String postUniqueNumbers);

    // 7. 验证码登录并操作职位
    public String loginAndOpPost(String corpCode,
                                 String userName,
                                 String password,
                                 int opType,
                                 String opChannelPositionList);

    public String changeValidateCode(String corpCode,
                                     String userName,
                                     String password,
                                     int channelDicId,
                                     String account);
    
    /**
     * 获取渠道发布数
     * @param corpCode
     * @param userName
     * @param pwd
     * @param channelAccount
     * @return 
     */
    public String getChannelPunlishPostNum(String corpCode,
                                           String userName,
                                           String pwd,
                                           String channelAccountXml);

}
