import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import weaver.general.TimeUtil;

@Slf4j
public class TimeTest {

    @Test
    public void toMd5Test() {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        log.info("currentTimeString : {}", currentTimeString);

    }
}
