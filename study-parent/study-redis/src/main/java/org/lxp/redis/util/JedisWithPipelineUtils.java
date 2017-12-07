package org.lxp.redis.util;

import static org.lxp.redis.util.JedisUtils.getResource;

import java.util.Collections;
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

    public static long zadd(String key, double score, String value) {
        long rtn = 0;
        try (Jedis jedis = getResource()) {
            Pipeline pipeline = jedis.pipelined();
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
    public static void zadd(String key, Map<String, Double> values) {
        try (Jedis jedis = getResource()) {
            Pipeline pipeline = jedis.pipelined();
            for (Entry<String, Double> entry : values.entrySet()) {
                pipeline.zadd(key, entry.getValue(), entry.getKey());
            }
            pipeline.sync();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static Set<String> zrange(String key, int start, int end) {
        Set<String> rtn = Collections.emptySet();
        try (Jedis jedis = getResource()) {
            Pipeline pipeline = jedis.pipelined();
            Response<Set<String>> response = pipeline.zrange(key, start, end);
            pipeline.sync();
            rtn = response.get();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }

    public static long zrem(String key, List<String> values) {
        long rtn = 0;
        try (Jedis jedis = getResource()) {
            Pipeline pipeline = jedis.pipelined();
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
    public static Set<String> zpop(String key, int offset) {
        Set<String> rtn = Collections.emptySet();
        try (Jedis jedis = getResource()) {
            Pipeline pipeline = jedis.pipelined();
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

    public static long del(String key) {
        long rtn = 0;
        try (Jedis jedis = getResource()) {
            Pipeline pipeline = jedis.pipelined();
            Response<Long> response = pipeline.del(key);
            pipeline.sync();
            rtn = response.get();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }
}
