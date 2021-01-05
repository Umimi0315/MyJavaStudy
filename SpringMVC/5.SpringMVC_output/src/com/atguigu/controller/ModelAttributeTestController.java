package com.atguigu.controller;

import com.atguigu.bean.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.Oneway;
import java.util.Map;

/**
 * 测试ModelAttribute注解：
 * 使用场景；书城的图书修改为例：
 * 1）页面端：
 *      显示要修改的图书信息，图书的所有字段都在
 * 2）servlet收到请求，调用dao
 *      String sql="update bs_book set title=?,
 *                  author=?,price=?sales=?,
 *                  stock=?,img_path=?
 *                  where id=?"
 * 3)实际场景？
 *      并不是全字段修改；只会修改部分字段，以修改用户信息为例
 *      username    password    address
 *      1)、不修改的字段可以在页面进行展示但是不要提供修改输入框；
 *      2）、为了简单，Controller直接在参数位置来写Book对象
 *      3）、SpringMVC为我们自动封装book；（没有带的值是null）
 *      4）、如果接下来调用了一个全字段更新的dao操作，会将其他的字段可能变为null
 * 4）、如何能保证全字段更新的时候，只更新了页面携带的数据；
 *      1）、修改dao；代价大？
 *      2）、Book对象是如何封装的？
 *          1)、SpringMVC创建一个book对象，每一个属性都有默认值，bookName就是null
 *              1.让SpringMVC别创建book对象，直接从数据库中先取出一个id=100的Book对象的信息
 *              2.Book{id=100, bookName=西游记, author='张三', stock=12, sales=15, price=98.98}
 *          2）、将请求中所有与book对应的属性一一设置过来；
 *              3.使用刚才从数据库中取出的book对象给它的里面设置值；（请求参数带了哪些值就覆盖之前的值）
 *              4.带了的字段就改为携带的值，没带的字段就保持之前的值
 *          3）、调用全字段更新就有问题
 *              5.将之前从数据库中查到的对象，并且封装了请求参数的对象，进行保存
 *
 * @SessionAttribute(value="haha")
 *      最好不要使用：
 *          1）、为了避免可能引发的异常
 *                  保证两点：
 *                      1、要么隐含模型中有@SessionAttribute标注的属性
 *                      2、如果隐含模型中没有，session还说有就一定要有，否则抛异常
 *
 *
 */
@Controller
public class ModelAttributeTestController {

    public Object o1;
    public Object o2;

    public Object b1;
    public Object b2;
    /**
     * 可以告诉SpringMVC不要new这个book了我刚才保存了一个book；
     * 那个就是从数据库中查询出来的，用我这个book
     *
     * 同都是BindingAwareModelMap
     * @param book
     * @return
     */
    @RequestMapping("/updateBook")
    public String updateBook(@ModelAttribute("book")Book book, Map<String,Object> model){
        System.out.println("页面要提交过来的图书信息："+book);
        o2=model;
        b2=model.get("book");
        System.out.println("o1==o2?"+(o1==o2));
        System.out.println("b1==b2?"+(b1==b2)+"-->"+(b2==book));
        //bookDao.update(book);
        /**
         *      String sql="update bs_book set title=?,
         *                  author=?,price=?sales=?,
         *                  stock=?,img_path=?
         *                  where id=?"
         */
        return "success";
    }

    /**
     * 1)、SpringMVC要封装请求参数的Book对象不应该是自己new出来的
     *      而应该是【从数据库中】拿到的准备好的对象
     * 2）、再来使用这个对象封装请求参数
     *
     * @ModelAttribute:
     *      参数：取出刚才保存的数据
     *      方法位置：这个方法就会提前于目标方法先运行;
     *              1)我们可以在这里提前查出数据库中图书的信息
     *              2)将这个图书信息保存起来（方便下一个方法还能使用）
     * 参数map：BindingAwareModelMap
     *
     * @ModelAttribute 标注的方法会提前运行并把方法的运行结果放在隐含模型中
     *      放的时候会使用一个key：
     *          如果 @ModelAttribute("book")制定了，就用指定的book
     *          如果没指定就用返回值类型的首字母小写作为key
     *
     */
    @ModelAttribute
    public void hahaMyModelAttribute(Map<String ,Object> map){
        Book book=new Book(100,"西游记","吴承恩",98,10,98.98);
        System.out.println("数据库中查到的图书信息是："+book);
        map.put("book", book);
        b1=book;
        o1=map;
        System.out.println("modelAttribute方法...查询了图书并给你保存起来了...他的参数map类型为："+map.getClass());
    }
}
