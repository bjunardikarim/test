package id.apps.app.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtoHelper {

    public static DtoResponsePaging constructResponsePaging(StatusMsgEnum sme, Map<String, Object> msg, List<?> data, int total) {
        DtoResponse dtoResponse = constructResponse(sme, msg, data);
        DtoResponsePaging dtoResponsePaging = new DtoResponsePaging();
        dtoResponsePaging.setStatus(dtoResponse.getStatus());
        dtoResponsePaging.setData(dtoResponse.getData());
        dtoResponsePaging.setMessage(dtoResponse.getMessage());
        dtoResponsePaging.setTotal(total);
        return dtoResponsePaging;
    }

    public static DtoResponse constructResponse(StatusMsgEnum sme, Map<String, Object> msg, List<?> data) {
        DtoResponse dtoRespond = null;
        if (null != sme) {
            switch (sme) {
                case GAGAL:
                    dtoRespond = new DtoResponse();
                    dtoRespond.setStatus(CommonConstant._0);
                    dtoRespond.addMessage(CommonConstant.MESSAGE, CommonConstant.GAGAL);
                    break;
                case SUKSES:
                    dtoRespond = new DtoResponse();
                    dtoRespond.setStatus(CommonConstant._1);
                    dtoRespond.addMessage(CommonConstant.MESSAGE, CommonConstant.SUKSES);
                    break;
                default:
                    return null;
            }
        }
        if (msg != null) {
            for (Map.Entry<String, Object> entry : msg.entrySet()) {
                dtoRespond.addMessage(entry.getKey(), entry.getValue());
            }
        }
        if (data != null) {
            dtoRespond.setData(data);
        }
        return dtoRespond;
    }

    public static DtoResponse constructResponse(StatusMsgEnum sme, Map<String, Object> msg, String note, Map<String, List<String>> detailMessage, List<?> data) {
        if (note != null) {
            if (msg == null) {
                msg = new HashMap<String, Object>();
            }
            msg.put("note", note);
        }
        if (detailMessage != null) {
            if (msg == null) {
                msg = new HashMap<String, Object>();
            }
            msg.put("details", detailMessage);
        }
        DtoResponse dtoResponse = constructResponse(sme, msg, data);
        return dtoResponse;
    }

    public static DtoResponse constructResponseInvalidRequest() {
        DtoResponse dtoResponse = new DtoResponse();
        dtoResponse.setStatus("0");
        dtoResponse.addMessage("authentication", "Invalid Request");
        dtoResponse.addMessage("authorization", "Invalid Request");
        return dtoResponse;
    }

   
}

