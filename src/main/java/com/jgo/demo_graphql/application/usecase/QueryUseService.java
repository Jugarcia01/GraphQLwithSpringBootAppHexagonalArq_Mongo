package com.jgo.demo_graphql.application.usecase;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.stereotype.Component;
// import com.coxautodev.graphql.tools.GraphQLQueryResolver;

// Uncomment the annotation to enable QueryUseService, che
@Component
public class QueryUseService {
//public class QueryUseService implements GraphQLQueryResolver {

  public String firstQuery() {
    return "First Query Executed!";
  }

  public String generateFullName (String firstName, String lastName) {
    return firstName + " " + lastName;
  }

  public Float generateAverage (ArrayList<Integer> numbers) {
    // var sum = 0.0f;
    Float sum = 0.0f;

    if (numbers.size() == 0) {
      return sum;
    }
    for (Integer number : numbers) {
      sum += number;
    }
    return sum / numbers.size();
  }

  public Float generateAverageFunctional (ArrayList<Integer> numbers) {
    AtomicReference<Float> sum = new AtomicReference<>(0.0f);
    if (numbers.size() == 0) {
      return sum.get();
    }
    numbers.parallelStream().forEach(number -> sum.updateAndGet(n -> n + number));
    return sum.get() / numbers.size();
  }

}
