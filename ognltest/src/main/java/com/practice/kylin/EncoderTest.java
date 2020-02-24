package com.practice.kylin;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.kylin.common.KylinConfig;
import org.apache.kylin.common.util.DateFormat;
import org.apache.kylin.cube.CubeInstance;
import org.apache.kylin.cube.CubeManager;
import org.apache.kylin.cube.cuboid.Cuboid;
import org.apache.kylin.cube.kv.RowKeyDecoder;
import org.apache.kylin.cube.kv.RowKeyEncoder;
import org.apache.kylin.cube.model.CubeDesc;

public class EncoderTest extends LocalFileMetadataTestCase{
	
	public static void main(String[] args) throws IOException {
		
		
		CubeInstance cube = CubeManager.getInstance(getTestConfig()).getCube("TEST_KYLIN_CUBE_WITHOUT_SLR_READY");
        CubeDesc cubeDesc = cube.getDescriptor();

        String[] data = new String[8];
        data[0] = "2012-12-15";
        data[1] = "11848";
        data[2] = "Health & Beauty";
        data[3] = "Fragrances";
        data[4] = "Women";
        data[5] = "刊登格式测试";// UTF-8
        data[6] = "0";
        data[7] = "15";

        long baseCuboidId = Cuboid.getBaseCuboidId(cubeDesc);
        Cuboid baseCuboid = Cuboid.findForMandatory(cubeDesc, baseCuboidId);
        RowKeyEncoder rowKeyEncoder = new RowKeyEncoder(cube.getFirstSegment(), baseCuboid);

        byte[] encodedKey = rowKeyEncoder.encode(data);
        assertEquals(22 + rowKeyEncoder.getHeaderLength(), encodedKey.length);

        RowKeyDecoder rowKeyDecoder = new RowKeyDecoder(cube.getFirstSegment());
        rowKeyDecoder.decode(encodedKey);
        List<String> values = rowKeyDecoder.getValues();
        assertEquals("[" + millis("2012-12-15") + ", 11848, Health & Beauty, Fragrances, Women, 刊登格式, 0, 15]", values.toString());
		
	}
	
	private static String millis(String dateStr) {
        return String.valueOf(DateFormat.stringToMillis(dateStr));
    }

}
