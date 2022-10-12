package com.taf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaunchPojo {
    private int id;
    private int total;
    private int passed;
    private int failed;
    private int skipped;
    private int productBugs;
    private int autoBugs;
    private int systemIssues;
    private int toInvestigate;
}
