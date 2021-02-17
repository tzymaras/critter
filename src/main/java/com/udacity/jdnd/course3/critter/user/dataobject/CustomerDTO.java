package com.udacity.jdnd.course3.critter.user.dataobject;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;
}
