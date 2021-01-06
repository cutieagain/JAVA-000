package io.kimmking.rpcfx.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoSerializer implements Serializer {
//    private final Class<?> ct;
    // kryo 是非线程安全类
    final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
//            Kryo kryo = new Kryo();
//            kryo.register(ct, new BeanSerializer(kryo, ct));
//            return kryo;
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.register(NettyMessage.class);
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
            return kryo;
        }
    };

   /* public KryoSerializer(Class<?> ct) {
        this.ct = ct;
    }*/

//    public Class<?> getCt() {
//        return ct;
//    }

    private Kryo getKryo() {
        return kryoLocal.get();
    }

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        try {
            Kryo kryo = getKryo();
            kryo.writeObjectOrNull(output, obj, obj.getClass());
            output.flush();
            return bos.toByteArray();
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(bos);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Object obj) {
        if (bytes == null)
            return null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(bais);
        try {
            Kryo kryo = getKryo();
            return (T) kryo.readObjectOrNull(input, obj.getClass());
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(bais);
        }
    }
}
