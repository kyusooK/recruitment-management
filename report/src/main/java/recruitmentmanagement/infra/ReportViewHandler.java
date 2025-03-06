package recruitmentmanagement.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import recruitmentmanagement.config.kafka.KafkaProcessor;
import recruitmentmanagement.domain.*;

@Service
public class ReportViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ReportRepository reportRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAnalyzeDataRegistered_then_CREATE_1(
        @Payload AnalyzeDataRegistered analyzeDataRegistered
    ) {
        try {
            if (!analyzeDataRegistered.validate()) return;

            // view 객체 생성
            Report report = new Report();
            // view 객체에 이벤트의 Value 를 set 함
            report.setTotalApplyCount(
                analyzeDataRegistered.getTotalApplyCount()
            );
            report.setPassedCount(analyzeDataRegistered.getPassedCount());
            // view 레파지 토리에 save
            reportRepository.save(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAnalyzeDataUpdated_then_UPDATE_1(
        @Payload AnalyzeDataUpdated analyzeDataUpdated
    ) {
        try {
            if (!analyzeDataUpdated.validate()) return;
            // view 객체 조회
            Optional<Report> reportOptional = reportRepository.findById(
                analyzeDataUpdated.getId()
            );

            if (reportOptional.isPresent()) {
                Report report = reportOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                report.setTotalApplyCount(
                    analyzeDataUpdated.getTotalApplyCount()
                );
                report.setPassedCount(analyzeDataUpdated.getPassedCount());
                // view 레파지 토리에 save
                reportRepository.save(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
