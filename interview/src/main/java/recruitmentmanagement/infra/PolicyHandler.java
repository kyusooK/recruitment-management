package recruitmentmanagement.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import recruitmentmanagement.config.kafka.KafkaProcessor;
import recruitmentmanagement.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    InterviewRepository interviewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ResumePassed'"
    )
    public void wheneverResumePassed_SetInterviewSchedule(
        @Payload ResumePassed resumePassed
    ) {
        ResumePassed event = resumePassed;
        System.out.println(
            "\n\n##### listener SetInterviewSchedule : " + resumePassed + "\n\n"
        );

        // Sample Logic //
        Interview.setInterviewSchedule(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
