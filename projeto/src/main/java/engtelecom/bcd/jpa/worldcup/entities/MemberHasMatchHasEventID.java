package engtelecom.bcd.jpa.worldcup.entities;

import lombok.*;
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MemberHasMatchHasEventID {

    private Integer event;

    private MemberHasMatchHasEventID memberHasMatchHasEventID;
}
