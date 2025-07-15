package com.example.spring_batch_demo.spring_batch_demo.skip;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class JobSkipPolicy implements SkipPolicy {

  @Override
  public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {
    if (skipCount > 3) {
      return false;
    }
    return true;
  }
}
