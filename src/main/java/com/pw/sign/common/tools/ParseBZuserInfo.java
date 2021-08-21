package com.pw.sign.common.tools;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParseBZuserInfo {

    private static final String sqlTemp = "insert into sys_x_user (id, username, phone, head_img, nick_name, sex, create_id, update_id, create_where, create_time, update_time, salt, other) values";
    private static final int step = 300;

    public static void main(String[] args) {
//        String file = "F:\\self-job\\sigh-conf\\bz.txt";
        String file = "F:\\self-job\\other\\FindUserFromBIlibili\\bilibiliuser3.txt";
        List<String> lines = readFileContent(file);
        List<JSONObject> jsons = toJson(lines);
        List<String> sqlVals = sqlValsHandler(jsons);
        String stepSql = sqlTemp;
        for (int i = 0; i < sqlVals.size(); i++) {
            if(i!=0){
                if(i+1 == sqlVals.size() || i%step == 0){
                    stepSql += ";";
                    print(stepSql);
                    stepSql = sqlTemp;
                }else{
                    stepSql += ",";
                }
            }
            stepSql += sqlVals.get(i);
        }
    }

    private static void print(String stepSql) {
        System.out.println(stepSql);
        List<String> lines = new ArrayList<>();
        lines.add(stepSql);
        writeFileContent("F:\\self-job\\sigh-conf\\bilibiliUserInsert.sql", lines);
    }

    private static List<String> sqlValsHandler(List<JSONObject> jsons) {
        List<String> vals = new ArrayList<>();
        for (int i = 0; i < jsons.size(); i++) {
            String val = assembleSqlVal(jsons.get(i));
            if(StringUtils.isNotEmpty(val)){
                vals.add(val);
            }
        }
        return vals;
    }

    private static String assembleSqlVal(JSONObject jsonObject) {
        String val = "('%s', '%s', '%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
        JSONObject u = JSONObject.parseObject(JSONObject.toJSONString(RandomUserInfo.getAddress()));
        if(filterNo(jsonObject,"sign")){
            return null;
        }
        return String.format(val,
                UUID.randomUUID().toString(),
                jsonObject.getString("name"),
                u.getString("tel"),
                jsonObject.getString("face"),
                jsonObject.getString("name"),
                getSexTag(jsonObject.getString("sex")),
                "sys-web-bili",
                "sys-web-bili",
                "SYSTEM",
                "2021-08-21 19:42:00",
                "2021-08-21 19:42:00",
                UUID.randomUUID().toString(),
                getOther(jsonObject.getString("sign"))
                );
    }

    private static boolean filterNo(JSONObject jsonObject, String... vars) {
        for (String var: vars) {
            if(StringUtils.isEmpty(jsonObject.getString(var))){
                return true;
            }
        }
        return false;
    }

    private static String getOther(String sign) {
        JSONObject r = new JSONObject();
        r.put("sign", sign);
        return r.toJSONString();
    }

    private static int getSexTag(String sex) {
        if("男".equals(sex)){
            return 1;
        }
        if("女".equals(sex)){
            return 2;
        }
        return 3;
    }

    private static List<JSONObject> toJson(List<String> lines) {
        List<JSONObject> jsons = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if(StringUtils.isEmpty(lines.get(i))){
                continue;
            }
            jsons.add(JSONObject.parseObject(lines.get(i)).getJSONObject("data"));
        }
        return jsons;
    }

    private static void writeFileContent(String fileName, List<String> lines){
        try{
            FileWriter writer=new FileWriter(fileName,true);
            for (String line : lines) {
                writer.write(line+"\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> result = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                result.add(tempStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result;
    }
}
