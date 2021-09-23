package com.vinsys.sample.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinsys.sample.model.Sample;
@Repository
public interface SampleRepo extends JpaRepository<Sample, Integer> {


}
