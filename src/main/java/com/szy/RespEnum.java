package com.szy;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by shizhouyong on 2016/12/2.
 */
public enum RespEnum {

    //成功
    SUCCESS(0, "success"),

    /*
     * 公共错误码
	 */
    PARAMETER_MiSS(0x00001000, "miss required parameter"),     //缺少必要参数
    REQUEST_TIMEOUT(0x00001001, "request time out"),           //请求超时
    UNKNOWN_ERROR(0x00001002, "unknown error"),                //未知错误
    NOT_LOGIN(0x00001003, "not logged in"),                    //未登录

    /*
	 * 业务错误码
	 */
    NO_ACCESS(0x00010001, "no access"),                        //没有权限
    NO_USER(0x00010002, "no user"),                            //登录用户名或者密码错误
    NAME_DUPLICATE(0x00010003, "name duplicate"),              //用户名重复
    PASSWD_ERR(0x00010004, "password error"),                  //密码错误
    DATA_NOT_FOUND(0x00010005, "data not found"),              //该数据不存在
    TYPE_NOT_FOUND(0x00010006, "type not found"),              //类型不存在
    DATA_PARSE_ERR(0x00010007, "data parse failed"),           //数据解析出错
    MAJOR_IS_NULL(0x00010008, "major is null"),                //专业信息为空
    FIRST_CHOOSE_IS_NULL(0x00010009, "first choose is null"),  //第一志愿为空
    SECOND_CHOOSE_IS_NULL(0x0001000A, "second choose is null"),//第二志愿为空
    FILE_INPUTSTREAM_ERR(0x0001000B, "file change to inputStream failed"),
    FILE_NEWWORKBOOK_ERR(0x0001000C, "new workbook failed"),
    CATEGORY_NOT_FOUND(0x0002000D, "category is null"),
    POSITION_NOT_FOUND(0x0002000E, "position is null"),
    DUPLICATE_DATA(0x0002000F, "data duplicate"),
    PLAN_DATA_ERR(0x00020010, "plan data error"),              //分流计划数据有误
    PLAN_UNIT_DATA_ERR(0x00020011, "plan'unit data error"),

    /*
     * 数据库错误码
     */
    DATA_INSERT_ERR(0x00020001, "data insert err"),            //数据插入失败
    DATA_UPDATE_ERR(0x00020002, "data update err"),            //数据更新失败
    DATA_DELETE_ERR(0x00020003, "data delete err"),            //数据删除失败

    END(0x7FFFFFFF, "end");                                    //最大错误码

    private Response response = new Response();

    private RespEnum(int code, String info) {
        response.setRetCode(code);
        response.setRetInfo(info);
    }

    public Response getResponse() {
        return response;
    }

    public int getRetCode() {
        return response.getRetCode();
    }

    public String getRetInfo() {
        return response.getRetInfo();
    }

    @JsonValue
    public Response toMap() {
        return response;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(response);
    }
}
