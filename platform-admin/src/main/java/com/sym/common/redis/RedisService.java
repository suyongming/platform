package com.sym.common.redis;

import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * redis工具类 来自：https://www.cnblogs.com/superfj/p/9232482.html
 * @author zJun
 * @date 2018年10月30日 上午11:07:06
 */
public class RedisService {

    /**  - 默认过期时长，单位：秒  */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /** -不设置过期时长  */
    public static final long NOT_EXPIRE = -1;

    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> opsValue;

    private HashOperations<String, String, Object> opsHash;

    private ListOperations<String, Object> opsList;

    private SetOperations<String, Object> opsSet;

    private ZSetOperations<String, Object> opsZSet;

    public String getCatalog() {
        return catalog;
    }

    private String catalog;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.opsValue = redisTemplate.opsForValue();
        this.opsHash = redisTemplate.opsForHash();
        this.opsList = redisTemplate.opsForList();
        this.opsSet = redisTemplate.opsForSet();
        this.opsZSet = redisTemplate.opsForZSet();
        this.catalog = "default:";
    }

    /**
     * @param redisTemplate
     * @param catalog 目录
     */
    public RedisService(RedisTemplate<String, Object> redisTemplate, String catalog) {
        this.redisTemplate = redisTemplate;
        this.opsValue = redisTemplate.opsForValue();
        this.opsHash = redisTemplate.opsForHash();
        this.opsList = redisTemplate.opsForList();
        this.opsSet = redisTemplate.opsForSet();
        this.opsZSet = redisTemplate.opsForZSet();
        this.catalog = catalog + ":";
    }

    /**
     * 返回自增值
     * @param key
     * @param liveTime 秒
     * @return
     * @author zJun
     * @date 2019年1月25日 上午10:50:40
     */
    public Long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(this.catalog + key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if (null == increment || increment.longValue() == 0) {
            if (liveTime > 0) {
                entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
            }
        }
        return increment;
    }

    /**
     * 自增
     * @param key
     * @param liveTime
     * @return 返回结果值
     */
    public Long incrAndGet(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(this.catalog + key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.incrementAndGet();
        if (null == increment || increment.longValue() == 0) {
            if (liveTime > 0) {
                entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
            }
        }
        return increment;
    }

    /**
     * 自减
     * @param key
     * @param liveTime
     * @return 返回结果值
     */
    public Long decrAndGet(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(this.catalog + key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.decrementAndGet();
        if (null == increment || increment.longValue() == 0) {
            if (liveTime > 0) {
                entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
            }
        }
        return increment;
    }

    /**
     * 增加指定值
     * @param key
     * @param delta
     * @param liveTime
     * @return 返回结果值
     */
    public Long addAndGet(String key,long delta, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(this.catalog + key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.addAndGet(delta);
        if (null == increment || increment.longValue() == 0) {
            if (liveTime > 0) {
                entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
            }
        }
        return increment;
    }

    /**
     * 设置value（默认1天后失效）
     * @param key
     * @param value
     * @author zJun
     * @date 2018年11月7日 下午4:35:46
     */
    public void setValue(String key, String value) {
        this.setValue(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 设置value（sec秒后过期）
     * @param key
     * @param value
     * @param sec
     * @author zJun
     * @date 2018年11月7日 下午4:36:35
     */
    public void setValue(String key, Object value, long sec) {
        this.opsValue.set(this.catalog + key, value, sec, TimeUnit.SECONDS);
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     * @author zJun
     * @date 2018年11月7日 下午4:39:54
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(String key) {
        return (T) this.opsValue.get(this.catalog + key);
    }

    /**
     * 为散列添加或者覆盖一个 key-value键值对
     * @param key
     * @param hashKey
     * @param value
     * @author zJun
     * @date 2018年11月7日 下午5:54:12
     */
    public void hashPut(String key, String hashKey, Object value) {
        this.opsHash.put(this.catalog + key, hashKey, value);
    }

    /**
     * 为散列添加多个key-value键值对
     * @param key
     * @param map
     * @author zJun
     * @date 2018年11月7日 下午6:10:38
     */
    public void putAll(String key, Map<? extends String, ? extends Object> map) {
        this.opsHash.putAll(this.catalog + key, map);
    }

    /**
     * 获取key中hash散列中的值
     * @param key
     * @param hashKey
     * @return
     * @author zJun
     * @date 2018年11月7日 下午5:59:29
     */
    @SuppressWarnings("unchecked")
    public <T> T hashGet(String key, String hashKey){
        return (T) this.opsHash.get(this.catalog + key, hashKey);
    }

    /**
     * 获取散列的value集合
     * @param key
     * @return
     * @author zJun
     * @date 2018年11月7日 下午6:11:58
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> hashValues(String key){
        return (List<T>) this.opsHash.values(this.catalog + key);
    }

    /**
     * 得到多个key的值。
     * @param key
     * @param hashKeys
     * @return
     * @author zJun
     * @date 2018年11月7日 下午6:03:53
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> hashMultiGet(String key, Collection<String> hashKeys) {
        return (List<T>) this.opsHash.multiGet(this.catalog + key, hashKeys);
    }

    /**
     * 获取散列的key-value键值对集合
     * @param key
     * @return
     * @author zJun
     * @date 2018年11月7日 下午6:13:29
     */
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> hashEntries(String key){
        return (Map<String, T>) this.opsHash.entries(this.catalog + key);
    }

    /**
     * 为散了中某个值加上 整型 delta
     * @param key
     * @param hashKey
     * @param delta
     * @return
     * @author zJun
     * @date 2018年11月7日 下午6:08:02
     */
    public Long hashIncrement(String key, String hashKey, long delta) {
        return this.opsHash.increment(this.catalog + key, hashKey, delta);
    }

    /**
     * 为散了中某个值加上 double delta
     * @param key
     * @param hashKey
     * @param delta
     * @return
     * @author zJun
     * @date 2018年11月7日 下午6:08:30
     */
    public Double hashIncrement(String key, String hashKey, double  delta) {
        return this.opsHash.increment(this.catalog + key, hashKey, delta);
    }


    /**
     * 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
     *  (下标 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。)
     * @param key
     * @param start 开始下标
     * @param end 结束下标
     * @return
     * @author zJun
     * @date 2018年11月7日 下午6:40:59
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> listRange(String key, long start, long end) {
        return (List<T>) this.opsList.range(this.catalog + key, start, end);
    }

    /**
     * 让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
     * (下标 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。)
     * @param key
     * @param start
     * @param end
     * @author zJun
     * @date 2018年11月7日 下午6:45:14
     */
    public void listTrim(String key, long start, long end) {
        this.opsList.trim(this.catalog + key, start, end);
    }

    /**
     * 是左面进入 也就是靠前 先进后出
     * @param key
     * @param value
     * @author zJun
     * @date 2018年11月7日 下午6:48:12
     */
    public void listLeftPush(String key, Object value) {
        this.opsList.leftPush(this.catalog + key, value);
    }

    /**
     * 多个值同 listLeftPush
     * @param key
     * @param values
     * @author zJun
     * @date 2018年11月7日 下午6:52:03
     */
    public void listLeftPushAll(String key, Collection<Object> values) {
        this.opsList.leftPushAll(this.catalog + key, values);
    }

    /**
     * 多个值同 listLeftPush
     * @param key
     * @param values
     * @author zJun
     * @date 2018年11月7日 下午6:52:03
     */
    public void listLeftPushAll(String key, Object... values) {
        this.opsList.leftPushAll(this.catalog + key, values);
    }


    /**
     * 顺序执行，先进先出
     * @param key
     * @param value
     * @author zJun
     * @date 2018年11月7日 下午6:54:38
     */
    public void listRightPush(String key, Object value) {
        this.opsList.rightPush(this.catalog + key, value);
    }

    /**
     * 顺序执行，先进先出同listRightPush
     * @param key
     * @param values
     * @author zJun
     * @date 2018年11月7日 下午6:56:15
     */
    public void listRightPushAll(String key, Collection<?> values) {
        this.opsList.leftPushAll(this.catalog + key, values);
    }

    /**
     * 顺序执行，先进先出同listRightPush
     * @param key
     * @param values
     * @author zJun
     * @date 2018年11月7日 下午6:56:39
     */
    public void listRightPushAll(String key, Object... values) {
        this.opsList.leftPushAll(this.catalog + key, values);
    }

    /**
     * 删除元素
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     * @param key
     * @param count
     * @param value
     * @author zJun
     * @date 2018年11月7日 下午7:00:05
     */
    public void listRemove(String key, long count, Object value) {
        this.opsList.remove(this.catalog + key, count, value);
    }

    /**
     * 命令用于通过索引获取列表中的元素。你也可以使用负数下标
     * (下标 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。)
     * @param key
     * @param index
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:02:24
     */
    @SuppressWarnings("unchecked")
    public <T> T listIndex(String key, long index) {
        return (T) this.opsList.index(this.catalog + key, index);
    }


    /**
     * 给集合key添加多个值，集合不存在创建后再添加
     * @param key
     * @param values
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:08:06
     */
    public Long opsSetAdd(String key, Object... values) {
        return this.opsSet.add(this.catalog + key, values);
    }


    /**
     * 移除集合中多个value值
     * @param key
     * @param values
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:09:32
     */
    public Long opsSetRemove(String key, Object... values) {
        return this.opsSet.remove(this.catalog + key, values);
    }


    /**
     * 随机删除集合中的一个值，并返回。
     * @param key
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:10:38
     */
    @SuppressWarnings("unchecked")
    public <T> T opsSetPop(String key) {
        return (T) this.opsSet.pop(this.catalog + key);
    }

    /**
     * 把源集合中的一个元素移动到目标集合。成功返回true.
     * @param key
     * @param value
     * @param destKey
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:11:53
     */
    public Boolean opsSetMove(String key, Object value, String destKey) {
        return this.opsSet.move(this.catalog + key, value, destKey);
    }

    /**
     * 检查集合中是否包含某个元素
     * @param key
     * @param value
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:12:54
     */
    public Boolean opsSetIsMember(String key, Object value) {
        return this.opsSet.isMember(this.catalog + key, value);
    }

    /**
     * 求指定集合与另一个集合的交集
     * @param key
     * @param otherKey
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:14:13
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> opsSetIntersect(String key, String otherKey){
        return (Set<T>) this.opsSet.intersect(this.catalog + key, this.catalog + otherKey);
    }

    /**
     * 肾用！！（没搞明白otherKeys指的是否是redis中key set中的key集合）
     * 求指定集合与另一个集合的交集
     * @param key
     * @param otherKeys
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:19:28
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> opsSetIntersect(String key, Collection<String> otherKeys){
        return (Set<T>) this.opsSet.intersect(this.catalog + key, this.catalog + otherKeys);
    }

    /**
     * 求指定集合与另一个集合的差集
     * @param key
     * @param otherKey
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:24:38
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> opsSetDifference(String key, String otherKey){
        return (Set<T>) this.opsSet.difference(this.catalog + key, this.catalog + otherKey);
    }

    /**
     * 求指定集合与另一个集合的差集
     * 肾用！！（没搞明白otherKeys指的是否是redis中key set中的key集合）
     * @param key
     * @param otherKeys
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:24:51
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> opsSetDifference(String key, Collection<String> otherKeys){
        return (Set<T>) this.opsSet.difference(key, this.catalog + otherKeys);
    }

    /**
     * 获取集合中的所有元素
     * @param key
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:26:58
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> opsSetMembers(String key){
        return (Set<T>) this.opsSet.members(this.catalog + key);
    }

    /**
     * 随机获取集合中的一个元素
     * @param key
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:27:09
     */
    @SuppressWarnings("unchecked")
    public <T> T opsSetRandomMember(String key) {
        return (T) this.opsSet.randomMember(this.catalog + key);
    }

    /**
     * 随机返回集合中指定数量的元素。随机的元素不会重复
     * @param key
     * @param count
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:27:33
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> opsSetDistinctRandomMembers(String key, long count){
        return (Set<T>) this.opsSet.distinctRandomMembers(this.catalog + key, count);
    }

    /**
     *  随机返回集合中指定数量的元素。随机的元素可能重复
     * @param key
     * @param count
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:27:59
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> opsSetRandomMembers(String key, long count){
        return (Set<T>) this.opsSet.randomMembers(this.catalog + key, count);
    }


    /**
     * 判断是否存在key
     * @param key
     * @return
     * @author zJun
     * @date 2018年11月7日 下午7:06:49
     */
    public boolean existsKey(String key) {
        return redisTemplate.hasKey(this.catalog + key);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(this.catalog + oldKey, this.catalog + newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(this.catalog + oldKey, this.catalog + newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(this.catalog + key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> this.catalog + k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> this.catalog + k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(this.catalog + key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(this.catalog + key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(this.catalog + key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(this.catalog + key);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ValueOperations<String, Object> getOpsValue() {
        return opsValue;
    }

    public void setOpsValue(ValueOperations<String, Object> opsValue) {
        this.opsValue = opsValue;
    }

    public HashOperations<String, String, Object> getOpsHash() {
        return opsHash;
    }

    public void setOpsHash(HashOperations<String, String, Object> opsHash) {
        this.opsHash = opsHash;
    }

    public ListOperations<String, Object> getOpsList() {
        return opsList;
    }

    public void setOpsList(ListOperations<String, Object> opsList) {
        this.opsList = opsList;
    }

    public SetOperations<String, Object> getOpsSet() {
        return opsSet;
    }

    public void setOpsSet(SetOperations<String, Object> opsSet) {
        this.opsSet = opsSet;
    }

    public ZSetOperations<String, Object> getOpsZSet() {
        return opsZSet;
    }

    public void setOpsZSet(ZSetOperations<String, Object> opsZSet) {
        this.opsZSet = opsZSet;
    }
}

