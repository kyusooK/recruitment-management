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
    ApplicationAnalyzeDataRepository applicationAnalyzeDataRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InterviewResultSaved'"
    )
    public void wheneverInterviewResultSaved_CollectData(
        @Payload InterviewResultSaved interviewResultSaved
    ) {
        InterviewResultSaved event = interviewResultSaved;
        System.out.println(
            "\n\n##### listener CollectData : " + interviewResultSaved + "\n\n"
        );

        // Sample Logic //
        ApplicationAnalyzeData.collectData(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
