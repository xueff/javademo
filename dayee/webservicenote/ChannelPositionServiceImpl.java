
package com.dayee.wintalent.service.position;

import org.apache.axis.encoding.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dayee.wintalent.framework.utils.StringUtils;
import com.dayee.wintalent.service.AbstractExternalService;
import com.dayee.wintalent.service.position.channel.ChannelPositionLogic;

public class ChannelPositionServiceImpl extends AbstractExternalService
        implements ChannelPositionService {

    private ChannelPositionLogic channelPositionLogic;

    private static final Log     logger = LogFactory
                                                .getLog(ChannelPositionServiceImpl.class);

    @Override
    public String addAndModifyPost(String corpCode,
                                   String userName,
                                   String password,
                                   String channelPostXml) {

        if (logger.isDebugEnabled()) {
            logger.debug("addAndModifyPost(corpCode=" + corpCode
                         + ", userName="
                         + userName
                         + ", password="
                         + password
                         + ", channelPostXml="
                         + channelPostXml
                         + ") - start");
        }

        valid(corpCode, userName, password);

        String xmlContent = channelPositionLogic
                .addOrModifyPosition(corpCode, channelPostXml);

        if (logger.isDebugEnabled()) {
            logger.debug("addAndModifyPost接口结束，返回结果：" + xmlContent);
        }
        return xmlContent;
    }

    @Override
    public String opPost(String corpCode,
                         String userName,
                         String password,
                         int opType,
                         String postUniqueNumbers) {

        if (logger.isDebugEnabled()) {
            logger.debug("opPost(corpCode=" + corpCode
                         + ", userName="
                         + userName
                         + ", password="
                         + password
                         + ", channelPostXml="
                         + postUniqueNumbers
                         + ") - start");
        }

        valid(corpCode, userName, password);

        String[] postUniqueNumber = null;
        if (StringUtils.hasLength(postUniqueNumbers, true)) {
            postUniqueNumber = postUniqueNumbers.split(StringUtils.COMMA);
        }

        String xmlContent = channelPositionLogic.opPost(corpCode, opType,
                                                        postUniqueNumber);

        if (logger.isDebugEnabled()) {
            logger.debug("opPost接口结束，返回结果：" + xmlContent);
        }
        return xmlContent;
    }

    @Override
    public String loginAndOpPost(String corpCode,
                                 String userName,
                                 String password,
                                 int opType,
                                 String opChannelPositionList) {

        if (logger.isDebugEnabled()) {
            logger.debug("loginAndOpPost(corpCode=" + corpCode
                         + ", userName="
                         + userName
                         + ", password="
                         + password
                         + ", opChannelPositionList="
                         + opChannelPositionList
                         + ") - start");
        }

        valid(corpCode, userName, password);

        String xmlContent = channelPositionLogic
                .loginAndOpPost(corpCode, opType, opChannelPositionList);

        if (logger.isDebugEnabled()) {
            logger.debug("loginAndOpPost接口结束，返回结果：" + xmlContent);
        }
        return xmlContent;
    }

    public String changeValidateCode(String corpCode,
                                     String userName,
                                     String password,
                                     int channelDicId,
                                     String account) {

        if (logger.isDebugEnabled()) {
            logger.debug("changeValidateCode(corpCode=" + corpCode
                         + ", userName="
                         + userName
                         + ", password="
                         + password
                         + ", channelDicId="
                         + channelDicId
                         + ", account="
                         + account
                         + ") - start");
        }

        valid(corpCode, userName, password);

        byte[] xmlContent = channelPositionLogic
                .changeValidateCode(corpCode, channelDicId, account);

        String xmlContentStr = StringUtils.EMPTY;
        if (xmlContent != null) {
            xmlContentStr = Base64.encode(xmlContent);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("changeValidateCode接口结束，返回结果：" + xmlContentStr);
        }
        return xmlContentStr;
    }

    public ChannelPositionLogic getChannelPositionLogic() {

        return channelPositionLogic;
    }

    public void setChannelPositionLogic(ChannelPositionLogic channelPositionLogic) {

        this.channelPositionLogic = channelPositionLogic;
    }
    
    @Override
    public String getChannelPunlishPostNum(String corpCode,
                                           String userName,
                                           String password,
                                           String channelAccountXml){
        if (logger.isDebugEnabled()) {
            logger.debug("changeValidateCode(corpCode=" + corpCode
                         + ", userName="
                         + userName
                         + ", password="
                         + password
                         + ") - start");
        }

        valid(corpCode, userName, password);
        String xmlContent = channelPositionLogic
                .getChannelPunlishPostNum(corpCode, channelAccountXml,userName);

        if (logger.isDebugEnabled()) {
            logger.debug("changeValidateCode接口结束，返回结果：" + xmlContent);
        }
        // TODO Auto-generated method stub
        return xmlContent;
    }
}
