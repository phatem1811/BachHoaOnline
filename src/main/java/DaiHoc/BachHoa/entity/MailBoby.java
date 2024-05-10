package DaiHoc.BachHoa.entity;

import lombok.Builder;

@Builder
public record MailBoby(String to, String subject, String text) {

}
