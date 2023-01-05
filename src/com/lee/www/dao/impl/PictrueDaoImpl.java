package com.lee.www.dao.impl;

import com.lee.www.po.Picture;
import com.lee.www.util.JdbcUtils;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 20:39
 * @description 负责图片的CRUD
 */
public class PictrueDaoImpl extends BaseDaoImpl {
    // 本类操作的数据库表名
    private final String TABLE_NAME = " " + JdbcUtils.getTableName(Picture.class) + " ";


    /**
     * 表中所有字段对应的查询语句
     */
    private final String ALL_FIELD_NAME = " id,author_id,pictrue,gmt_create,gmt_modified ";
    private String[] ALL_FIELD_ARRAY = new String[]{ "id","author_id","pictrue","gmt_create","gmt_modified" };
    public boolean add(Picture picture){
        if(picture !=null){
            return super.insert(picture)==1;
        }
        return false;
    }

    public LinkedList<Picture> list(String authorId){
        if(authorId!=null){
            Picture picture = new Picture();
            picture.setId(authorId);
            LinkedList list=  new LinkedList<>();
            LinkedList<Picture> pictures = new LinkedList();
            list = super.queryWhereAndEquals(ALL_FIELD_ARRAY, picture);
            for(int i=0;i<list.size();i++){
                Picture pic = (Picture) list.get(i);
                pictures.add(pic);
            }
            return pictures;
        }
        return null;
    }

}
