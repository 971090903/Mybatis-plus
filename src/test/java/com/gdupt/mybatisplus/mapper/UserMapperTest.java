package com.gdupt.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdupt.mybatisplus.pojo.User;
import com.gdupt.mybatisplus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Test
    public void select(){
        User user = userMapper.selectById(1);
        List<User> users = userMapper.selectAll();
        System.out.println(users);
        List<User> users1 = userMapper.selectList(null);
        users1.forEach(System.out::println);
    }
    @Test
    public void test02(){
        List<User> list=new ArrayList<>();
        for (int i=0;i<5;i++){
            User user=new User();
            user.setName("xyg"+i);
            user.setAge(20+i);
            user.setEmail("@fast"+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        List<User> list1 = userService.list();
        list1.forEach(System.out::println);
    }
    @Test
    public void test03(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","a")
                .between("age",20,30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    @Test
    public void test04(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    @Test
    public void test05(){
        //定义修改的条件，即修改谁
        //默认为and，or需要被定义
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("age",20)
                        .like("name","a")
                                .or()
                                        .isNotNull("email");
        //定义修改的内容，即修改成什么
        User user=new User();
        user.setEmail("9852@qq.com");
        int update = userMapper.update(user, queryWrapper);
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Test
    public void test06(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","a")
                .and(i->i.gt("age",20).or().isNull("email"));

    }
    @Test
    public void  test07(){
        Page<User> page=new Page<>(2,3);
        userMapper.selectPage(page, null);
        System.out.println(page.getRecords());
    }
    @Test
    public void test08(){
        User user1 = userMapper.selectById(1L);
        User user2 = userMapper.selectById(1L);
        user1.setAge(user1.getAge()+1);
        userMapper.updateById(user1);
        user2.setAge(50);
        userMapper.updateById(user2);
        System.out.println(userMapper.selectById(1L));
    }
}
