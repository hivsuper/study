package org.lxp.redis.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class JedisWithPipelineUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JedisWithPipelineUtils.class);
    private JedisUtils jedisUtils;

    public JedisWithPipelineUtils(JedisUtils jedisUtils) {
        this.jedisUtils = jedisUtils;
    }

    public long zadd(String key, double score, String value) {
        long rtn = 0;
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            Response<Long> response = pipeline.zadd(key, score, value);
            pipeline.sync();
            rtn = response.get();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }

    /**
     * 批量添加sorted-set
     * 
     * @param key
     * @param values
     */
    public void zadd(String key, Map<String, Double> values) {
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            for (Entry<String, Double> entry : values.entrySet()) {
                pipeline.zadd(key, entry.getValue(), entry.getKey());
            }
            pipeline.sync();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Set<String> zrange(String key, int start, int end) {
        Set<String> rtn = Collections.emptySet();
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            Response<Set<String>> response = pipeline.zrange(key, start, end);
            pipeline.sync();
            rtn = response.get();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }

    public long zrem(String key, List<String> values) {
        long rtn = 0;
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            Response<Long> response = pipeline.zrem(key, values.toArray(new String[values.size()]));
            pipeline.sync();
            rtn = response.get();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }

    /**
     * 封装zrange+zrem
     * 
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zpop(String key, int offset) {
        Set<String> rtn = Collections.emptySet();
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            Response<Set<String>> setResponse = pipeline.zrange(key, 0, offset);
            pipeline.sync();
            rtn = setResponse.get();
            if (!rtn.isEmpty()) {
                pipeline.zrem(key, rtn.toArray(new String[rtn.size()]));
                pipeline.sync();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }

    public Map<String, String> setBatch(Map<String, String> setMap) {
        Map<String, Response<String>> map = new HashMap<>();
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            for (Entry<String, String> entry : setMap.entrySet()) {
                map.put(entry.getKey(), pipeline.set(entry.getKey(), entry.getValue()));
            }
            pipeline.sync();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        Map<String, String> rtn = new HashMap<>();
        for (Entry<String, Response<String>> entry : map.entrySet()) {
            rtn.put(entry.getKey(), entry.getValue().get());
        }
        return rtn;
    }

    public Map<String, String> getBatch(Set<String> keys) {
        Map<String, Response<String>> map = new HashMap<>();
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            for (String key : keys) {
                map.put(key, pipeline.get(key));
            }
            pipeline.sync();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        Map<String, String> rtn = new HashMap<>();
        for (Entry<String, Response<String>> entry : map.entrySet()) {
            rtn.put(entry.getKey(), entry.getValue().get());
        }
        return rtn;
    }

    public Map<String, Long> delBatch(Set<String> keys) {
        Map<String, Response<Long>> map = new HashMap<>();
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            for (String key : keys) {
                map.put(key, pipeline.del(key));
            }
            pipeline.sync();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        Map<String, Long> rtn = new HashMap<>();
        for (Entry<String, Response<Long>> entry : map.entrySet()) {
            rtn.put(entry.getKey(), entry.getValue().get());
        }
        return rtn;
    }

    public long del(String key) {
        long rtn = 0;
        try (Jedis jedis = jedisUtils.getResource(); Pipeline pipeline = jedis.pipelined()) {
            Response<Long> response = pipeline.del(key);
            pipeline.sync();
            rtn = response.get();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }
}
