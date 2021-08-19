package com.pw.sign.common.tools;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParseIdeaTag {

    static String sqlFormat = "insert into sign_idea_tag (id, parent_id, cn_name, en_name, create_time,creator,update_time,updater ) values  ('%s','%s','%s','','2021-08-19 16:00:00','SYSTEM','2021-08-19 16:00:00','SYSTEM');";

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        String file = "F:/self-job/sign/src/main/resources/doc/themeTag.txt";
        List<String> lines = readFileContent(file);
        List<List<String>> cats = liesHandler(lines);
        List<String> sql = sqlHandler(cats);
    }

    private static List<String> sqlHandler(List<List<String>> cats) {
        List<String> sqls = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            List<String> cat = cats.get(i);
            if (CollectionUtils.isEmpty(cat)) {
                continue;
            }
            String sql = catHandler(cat);
            sqls.add(sql);
        }
        return sqls;
    }


    private static String catHandler(List<String> cat) {
        System.out.println("");
        String sqls = "";
        String parentId = UUID.randomUUID().toString();
        for (int i = 0; i < cat.size(); i++) {
            String sql = "";
            if (i == 0) {
                sql = String.format(sqlFormat, parentId, parentId, cat.get(i));
            } else {
                sql = String.format(sqlFormat, UUID.randomUUID(), parentId, cat.get(i));
            }
            System.out.println(sql);
            sqls += sql;
        }
        return sqls;
    }

    private static List<List<String>> liesHandler(List<String> lines) {
        List<List<String>> cats = new ArrayList<>();
        List<String> in = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String e = lines.get(i);
            if (i == lines.size() - 1 || StringUtils.isEmpty(e)) {
                cats.add(in);
                in = new ArrayList<>();
                continue;
            }
            in.add(e);
        }
        return cats;
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
