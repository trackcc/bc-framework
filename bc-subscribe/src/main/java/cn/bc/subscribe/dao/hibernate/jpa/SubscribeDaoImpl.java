package cn.bc.subscribe.dao.hibernate.jpa;

import org.junit.Assert;

import cn.bc.core.query.condition.impl.AndCondition;
import cn.bc.core.query.condition.impl.EqualsCondition;
import cn.bc.core.query.condition.impl.NotEqualsCondition;
import cn.bc.orm.hibernate.jpa.HibernateCrudJpaDao;
import cn.bc.subscribe.dao.SubscribeDao;
import cn.bc.subscribe.domain.Subscribe;

/**
 * 订阅DAO接口的实现
 * 
 * @author lbj
 * 
 */
public class SubscribeDaoImpl extends HibernateCrudJpaDao<Subscribe> implements SubscribeDao {

	public Subscribe loadByEventCode(String eventCode) {
		Assert.assertNotNull(eventCode);
		EqualsCondition eq=new EqualsCondition("eventCode", eventCode);
		return this.createQuery().condition(eq).singleResult();
	}

	public boolean isUnique(String eventCode, Long id) {
		Assert.assertNotNull(eventCode);
		AndCondition ac = new AndCondition();
		ac.add(new EqualsCondition("eventCode", eventCode));
		
		if(id != null){
			ac.add(new NotEqualsCondition("id", id));
		}

		return !(this.createQuery().condition(ac).list().size()>0);
	}

}
