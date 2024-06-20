package com.busanit501.teamproject2.lcs.dto;

import java.util.List;

public class ApiResponse {

    private CmmMsgHeader cmmMsgHeader;
    private ResponseBody responseBody;

    public ApiResponse() {
    }

    public CmmMsgHeader getCmmMsgHeader() {
        return cmmMsgHeader;
    }

    public void setCmmMsgHeader(CmmMsgHeader cmmMsgHeader) {
        this.cmmMsgHeader = cmmMsgHeader;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class ResponseBody {
        private Items items;

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }
    }

    public static class Items {
        private List<Item> item;

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }
    }

    public static class Item {
        private String mainTitle;  // 필요한 필드명으로 변경
        private String itemCntnts; // 필요한 필드명으로 변경
        private String lat;        // 필요한 필드명으로 변경
        private String lng;        // 필요한 필드명으로 변경
        private String addr1;      // 필요한 필드명으로 변경
        private String usageDayWeekAndTime; // 필요한 필드명으로 변경
        private String mainImgThumb;        // 필요한 필드명으로 변경

        public String getMainTitle() {
            return mainTitle;
        }

        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }

        public String getItemCntnts() {
            return itemCntnts;
        }

        public void setItemCntnts(String itemCntnts) {
            this.itemCntnts = itemCntnts;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getAddr1() {
            return addr1;
        }

        public void setAddr1(String addr1) {
            this.addr1 = addr1;
        }

        public String getUsageDayWeekAndTime() {
            return usageDayWeekAndTime;
        }

        public void setUsageDayWeekAndTime(String usageDayWeekAndTime) {
            this.usageDayWeekAndTime = usageDayWeekAndTime;
        }

        public String getMainImgThumb() {
            return mainImgThumb;
        }

        public void setMainImgThumb(String mainImgThumb) {
            this.mainImgThumb = mainImgThumb;
        }
    }

    public static class CmmMsgHeader {
        private String errMsg;
        private String returnAuthMsg;
        private int returnReasonCode;

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getReturnAuthMsg() {
            return returnAuthMsg;
        }

        public void setReturnAuthMsg(String returnAuthMsg) {
            this.returnAuthMsg = returnAuthMsg;
        }

        public int getReturnReasonCode() {
            return returnReasonCode;
        }

        public void setReturnReasonCode(int returnReasonCode) {
            this.returnReasonCode = returnReasonCode;
        }
    }
}
