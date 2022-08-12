package test;

import com.MK.Dao.OrderMapper;
import com.MK.Dao.UserMapper;
import com.MK.domain.Order;
import com.MK.domain.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    private String ConfigFile="sqlMapConfig.xml";
//    查询操作
    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resource = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取session工厂对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //获得session绘画对象
        SqlSession sqlSession=sessionFactory.openSession();
        //执行操作
        List<User> selectList = sqlSession.selectList("com.MK.Dao.UserMapper.findAll");
        //打印数据
        System.out.println(selectList);
        //释放资源
        sqlSession.close();
    }

//插入操作
    @Test
    public void test2() throws IOException {
        User user=new User();
        user.setUsername("鸡哥");
        user.setPassword("124");

        //获得核心配置文件
        InputStream resource = Resources.getResourceAsStream(ConfigFile);
        //获取session工厂对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //获得session绘画对象
        SqlSession sqlSession=sessionFactory.openSession();
        //增添操作
        sqlSession.insert("com.MK.Dao.UserMapper.add",user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

//    修改
    @Test
        public void test3() throws IOException {
            User user=new User();
            user.setId(2);
            user.setUsername("jige2");
            user.setPassword("124");

            //获得核心配置文件
            InputStream resource = Resources.getResourceAsStream(ConfigFile);
            //获取session工厂对象
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resource);
            //获得session绘画对象
            SqlSession sqlSession=sessionFactory.openSession();
            //增添操作
            sqlSession.update("com.MK.Dao.UserMapper.update",user);
            //提交事务
            sqlSession.commit();
            //释放资源
            sqlSession.close();
        }

//    删除操作
@Test
    public void test4() throws IOException {


        //获得核心配置文件
        InputStream resource = Resources.getResourceAsStream(ConfigFile);
        //获取session工厂对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //获得session绘画对象
        SqlSession sqlSession=sessionFactory.openSession();
        //增添操作
        sqlSession.insert("com.MK.Dao.UserMapper.delete",0);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

//根据id查询
@Test
    public void test5() throws IOException {


        //获得核心配置文件
        InputStream resource = Resources.getResourceAsStream(ConfigFile);
        //获取session工厂对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //获得session绘画对象,并设置自动提交事务
        SqlSession sqlSession=sessionFactory.openSession(true);
        //增添操作
        User user = sqlSession.selectOne("com.MK.Dao.UserMapper.findByid", 2);
        System.out.println(user);
        //提交事务
    //    sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

@Test
    public void test6() throws IOException {
        //获得核心配置文件
        InputStream resource = Resources.getResourceAsStream(ConfigFile);
        //获取session工厂对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //获得session绘画对象,并设置自动提交事务
        SqlSession sqlSession=sessionFactory.openSession(true);
        //获取Mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        List<User> all = userMapper.findAll();
        System.out.println(all);

        User user = userMapper.findByid(2);
        System.out.println(user);
        sqlSession.close();
    }

@Test
    public void test7() throws IOException {
        InputStream resource=Resources.getResourceAsStream(ConfigFile);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession=sessionFactory.openSession(true);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user01=new User();
        user01.setId(1);
        User user1=userMapper.findByCondition(user01);
        System.out.println(user1);

        List<Integer> idlist=new ArrayList<>();
        idlist.add(1);
        idlist.add(3);
        List<User> userlist=userMapper.findByids(idlist);
        System.out.println(userlist);
        sqlSession.close();
    }

    //查询， 测试将J数据库中的long类型转化为java中的数据类型
    @Test
    public void test8() throws IOException {
        InputStream resource=Resources.getResourceAsStream(ConfigFile);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession=sessionFactory.openSession(true);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectbirthday(6);
        System.out.println(user);
        sqlSession.close();
    }

    //插入，将java中的Date数据类型转化为数据库中的long类型
    @Test
    public void test9() throws IOException {
        InputStream resource=Resources.getResourceAsStream(ConfigFile);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession=sessionFactory.openSession(true);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user=new User();
        user.setUsername("鸡你太美003");
        user.setPassword("abc");
        user.setBirthday(new Date());

        userMapper.savebirthday(user);
        sqlSession.close();
    }

//    分页插件测试
    @Test
    public void test10() throws IOException {
        InputStream resource=Resources.getResourceAsStream(ConfigFile);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession=sessionFactory.openSession(true);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        //设置分页操作,分多少页，每页多少条数据
        PageHelper.startPage(3,3);

        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }

        //获得分页相关参数
        PageInfo<User> pageInfo=new PageInfo<User>(userList);
        System.out.println("当前第几页："+pageInfo.getPageNum());
        System.out.println("前一页："+pageInfo.getPrePage());
        System.out.println("后一页："+pageInfo.getNextPage());
        System.out.println("第一页："+pageInfo.getFirstPage());
        System.out.println("最后一页："+pageInfo.getLastPage());
        System.out.println("每页显示条数："+pageInfo.getPageSize());
        System.out.println("是否是第一页："+pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页："+pageInfo.isIsLastPage());
        List<User> pageInfoList = pageInfo.getList();
        System.out.println(pageInfoList);

        sqlSession.close();
    }

    //表一对一测试
    @Test
    public void test11() throws IOException {
        InputStream resource=Resources.getResourceAsStream(ConfigFile);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession=sessionFactory.openSession(true);
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        List<Order> orderList = orderMapper.findOrderAndUser();
        for (Order order : orderList) {
            System.out.println(order);
        }
        sqlSession.close();
    }

//    表一对多测试
    @Test
    public void test12() throws IOException {
        InputStream resource=Resources.getResourceAsStream(ConfigFile);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession=sessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = userMapper.findorder();
        for (User user : list) {
            System.out.println(user);
        }

        sqlSession.close();
    }

}
