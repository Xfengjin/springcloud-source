import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
public class T2 {

    @Test
    public void getTime() {
        // ZonedDateTime now = ZonedDateTime.now(); // 默认时区
        // System.out.println(now);

        ZonedDateTime date = ZonedDateTime.of(2020, 12, 31, 20, 20,
                20, 20, ZoneId.of("Asia/Shanghai"));
        System.out.println(date);
    }
}
