package com.lee.www.test;

import com.lee.www.dao.inter.HotelDao;
import com.lee.www.po.Hotel;
import com.lee.www.util.BeanFactory;

import java.math.BigDecimal;
import java.util.LinkedList;

import static com.lee.www.util.UUIDUtils.getUUID;

/**
 * @description 测试酒店Dao
 */
public class TestHotelDao {
    public static void main(String[] args) {

        HotelDao hotelDao = (HotelDao) BeanFactory.getBean(BeanFactory.DaoType.HotelDao);

        /**
         * 测试查询酒店全部信息功能
         */
        System.out.println("测试查询酒店全部信息功能");
        Hotel hotel = hotelDao.getHotel("1020");
        if (hotel != null) {
            System.out.println(hotel.getNumber());
            System.out.println(hotel.getId());
            System.out.println(hotel.getStatus());
            System.out.println(hotel.getArea());
            System.out.println(hotel.getGmtModified());
        } else {
            System.out.println(hotel);
        }

        /**
         * 测试获取所有酒店信息的功能
         */
        System.out.println("测试获取所有酒店信息的功能");
        LinkedList list = hotelDao.getAllHotels();
        System.out.println(list.size());
        for (
                int i = 0; i < list.size(); i++) {
            hotel = (Hotel) list.get(i);
            System.out.println(hotel.getGmtModified());
            System.out.println(hotel.getNumber());
            System.out.println(hotel.getName());

        }
        /**
         * 测试通过酒店id删除酒店的功能
         */
        System.out.println("测试通过酒店id删除酒店的功能");
        System.out.println(hotelDao.deleteById("5"));
        /**
         * 测试通过编号删除酒店的功能
         */
        System.out.println("测试通过酒店编号删除酒店的功能");
        System.out.println(hotelDao.deleteByNumber("0"));
        /**
         * 测试更新酒店信息的功能
         */
        System.out.println("测试更新酒店信息的功能");
        hotel = hotelDao.getHotel("0000");
        if (hotel != null) {
            hotel.setArea(BigDecimal.valueOf(1000.90));
            System.out.println(hotelDao.update(hotel));
        }


        /**
         * 测试增加酒店的功能
         */
        System.out.println("测试增加酒店的功能");
        hotel = new

                Hotel();
        hotel.setNumber("1020");
        hotel.setPhoto("test2");
        System.out.println(hotelDao.addHotel(null));

        /**
         * 测试模糊查询
         */
        System.out.println(hotelDao.findByName("test").

                size());
        /**
         * 批量增加酒店
         */
        for (int i = 0; i < 30; i++) {
            hotel = new Hotel();
            hotel.setId(getUUID());
            hotel.setNumber("01020" + i);
            hotel.setName("广州精品酒店第" + i + "号酒店");
            hotel.setArea(new BigDecimal(i + 110));
            System.out.println("add hotel");
            hotelDao.addHotel(hotel);
        }


    }
}
