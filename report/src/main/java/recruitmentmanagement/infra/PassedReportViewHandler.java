package recruitmentmanagement.infra;

import recruitmentmanagement.domain.*;
import recruitmentmanagement.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PassedReportViewHandler {

//<<< DDD / CQRS
    @Autowired
    private PassedReportRepository passedReportRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAnalyzeDataRegistered_then_CREATE_1 (@Payload AnalyzeDataRegistered analyzeDataRegistered) {
        try {

            if (!analyzeDataRegistered.validate()) return;

            // view 객체 생성
            PassedReport passedReport = new PassedReport();
            // view 객체에 이벤트의 Value 를 set 함
            passedReport.setName(analyzeDataRegistered.getApplicantName());
            passedReport.setSummationScore(analyzeDataRegistered.getSummationScore());
            passedReport.setInterviewScore(Integer.parseInt(analyzeDataRegistered.getInterviewScore()));
            passedReport.setPosition(Integer.parseInt(analyzeDataRegistered.getPosition()));
            // view 레파지 토리에 save
            passedReportRepository.save(passedReport);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
//>>> DDD / CQRS
}

