package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CRUDTests2 {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNull("name")
                .ge("age", 12)
                .isNotNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("delete return count = " + result);
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Tom");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "Jack");
        map.put("age", 20);
        queryWrapper.allEq(map);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.notLike("name", "e")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }

    @Test
    public void testSelectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.in("id", 1, 2, 3);
        queryWrapper.inSql("id", "select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(queryWrapper);//返回值是Object列表
        objects.forEach(System.out::println);
    }

    /**
     * 不调用or则默认为使用 and 连
     */
    @Test
    public void testUpdate1() {
        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or()
                .between("age", 20, 30);
        int result = userMapper.update(user, userUpdateWrapper);
        System.out.println(result);
    }

    /**
     * 这里使用了lambda表达式，or中的表达式最后翻译成sql时会被加上圆括号
     *
     * UPDATE user SET name=?, age=?, update_time=?
     * WHERE deleted=0 AND name LIKE ?
     * OR ( name = ? AND age <> ? )
     */
    @Test
    public void testUpdate2() {
        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or(i -> i.eq("name", "李白").ne("age", 20));
        int result = userMapper.update(user, userUpdateWrapper);
        System.out.println(result);
    }

    @Test
    public void testSelectListOrderBy() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 注意：只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
     *
     */
    @Test
    public void testSelectListLast() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectListColumn() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //UPDATE user SET age=?, update_time=?, name=?, email = '123@qq.com' WHERE deleted=0 AND name LIKE ?
    @Test
    public void testUpdateSet() {
        //修改值
        User user = new User();
        user.setAge(99);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .set("name", "老李头")//除了可以查询还可以使用set设置修改的字段
                .setSql(" email = '123@qq.com'");//可以有子查询
        int result = userMapper.update(user, userUpdateWrapper);
    }



}