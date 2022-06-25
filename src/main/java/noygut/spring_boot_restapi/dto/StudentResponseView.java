package noygut.spring_boot_restapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponseView {

    List<StudentResponse> responses;
}
