
package com.bank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "branches",
    uniqueConstraints = @UniqueConstraint(name = "uk_branch_code", columnNames = "branchCode")
)
@Getter @Setter @NoArgsConstructor
public class Branch {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @NotBlank
    @Size(min = 3, max = 16)
    @Column(nullable = false, length = 16)
    private String branchCode;

    @NotBlank
    @Size(min = 3, max = 64)
    @Column(nullable = false, length = 64)
    private String branchName;

    @Size(max = 255)
    private String branchAddress;
}
