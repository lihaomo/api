import dao.OrderDao;

public class test {
    public static void main(String[] args) {
        OrderDao orderDao=new OrderDao();
        System.out.println(orderDao.deleteOrder("1"));

    }
}
