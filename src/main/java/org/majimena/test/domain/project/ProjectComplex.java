package org.majimena.test.domain.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.majimena.test.domain.User;

import java.io.Serializable;

/**
 * Created by todoken on 2015/06/06.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectComplex implements Serializable {

    private Long id;

    private String name;

    private String description;

    private User owner;

}
