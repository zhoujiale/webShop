package com.zjl.webshop.service.serviceImpl;/**
 * @Auther: zhou
 * @Date: 2018/12/20 11:03
 * @Description:
 */

import com.zjl.webshop.dao.CustomerDao;
import com.zjl.webshop.entity.Customer;
import com.zjl.webshop.response.WebResponse;
import com.zjl.webshop.service.CustomerService;
import com.zjl.webshop.shiro.PassWordHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.UUID;

/**
 *@ClassName CustomerServiceImpl
 *@Description 顾客接口实现类
 *@Author zhou
 *Date 2018/12/20 11:03
 *@Version 1.0
 **/
@Service
public class CustomerServiceImpl implements CustomerService{

    private static Logger log = Logger.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PassWordHelper passWordHelper;

    /**
     * @description 顾客注册
     * @author zhou
     * @created  2018/12/20 11:19    
     * @param 
     * @return 
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public WebResponse customerSign(Customer customer) {
        Customer customers = customerDao.findByNickName(customer.getNickName());
        int roleId = 1;
        if(customers != null){
           log.error("该昵称的顾客已经存在");
           return new WebResponse().error(405,"","该昵称的顾客已经存在");
        }
        customer.setCreateTime(new Date());
        customer.setStatus(true);
        //生成32位的盐
        customer.setSalt(UUID.randomUUID().toString());
        Customer newCustomer =passWordHelper.encryptPassword(customer);
        try{
            int customerCount = customerDao.addCustomer(newCustomer);
            if(customerCount == 0){
                throw new RuntimeException();
            }
            int roleCount = customerDao.addRole(newCustomer.getCustomerId(),roleId);
            if(roleCount == 0 ){
                throw new RuntimeException();
            }
        }catch (Exception e){
            log.error("注册失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new WebResponse().error(406,"","注册失败");
        }
        return new WebResponse().ok("注册成功");
    }



    /**
     * @description 通过昵称查找顾客
     * @author zhou
     * @created  2018/12/27 10:48    
     * @param 
     * @return 
     */
    @Override
    public Customer findByNickName(String nickName) {
        Customer customer = customerDao.findByNickName(nickName);
        return customer;
    }

    /**
     * @description 修改交易密码
     * @author zhou
     * @created  2018/12/27 11:31    
     * @param 
     * @return 
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public WebResponse addDealPassword(String nickName, String dealPassword, String reDealPassword) {
        Customer customer = customerDao.findByNickName(nickName);
        if(customer == null){
           log.error("不存在该用户");
           return new WebResponse().error(402,"","不存在该用户");
        }else {
           if(!dealPassword.equals(reDealPassword)){
               log.error("两次密码不一致");
               return new WebResponse().error(403,"","两次密码不一致");
           }else {
               customer.setDealSalt(UUID.randomUUID().toString());
               customer.setDealPassword(passWordHelper.createPassword(dealPassword,customer.getDealSalt()));
               try{
                    int updateCount = customerDao.updateDealPassword(nickName,customer.getDealPassword(),customer.getDealSalt());
                    if(updateCount == 0){
                        throw new RuntimeException();
                    }
               }catch (Exception e){
                   log.error("设置交易密码失败");
                  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                  return new WebResponse().error(404,"","设置交易密码失败");
               }
           }
        }
        return new WebResponse().ok("交易密码修改成功");
    }

    /**
     * @description 编辑顾客资料
     * @author zhou
     * @created  2018/12/27 13:59    
     * @param 
     * @return 
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public WebResponse editCustomer(Customer customer) {
        try{
            Customer customerByFind = customerDao.findByNickName(customer.getNickName());
            //判断和原密码是否一样
            if(customerByFind.getPassword().
                    equals(passWordHelper.createPassword(customer.getPassword(),customerByFind.getSalt()))){
                customer.setPassword(customerByFind.getPassword());
            }else {
                String salt = UUID.randomUUID().toString();
                customer.setSalt(salt);
                customer.setPassword(passWordHelper.createPassword(customer.getPassword(),salt));
            }
            int count = customerDao.editCustomer(customer);
        }catch (Exception e){
            log.error("更新资料失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new WebResponse().ok("更新成功");
    }
}
