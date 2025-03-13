
// 사용자 등록
http :8088/users name=김규수 email=rbtn110@uengine.org

http :8088/users name=김신입 email=rbtn110@gmail.com

// 이력서 접수
http :8088/resumes userId:='{"id":1}' position="시스템 유지보수" career="Senior Software Engineer at ABC Corp (2018 - Present): 주도적으로 대규모 웹 애플리케이션 개발 및 유지보수. 팀 리더로서 10명의 개발자 관리 및 프로젝트 일정 조율. 최신 기술 트렌드에 맞춘 시스템 아키텍처 설계. Software Developer at XYZ Inc (2015 - 2018): 클라우드 기반 솔루션 개발 및 최적화. 고객 요구사항 분석 및 기술적 해결책 제시." qualifications="자격요건: 컴퓨터 공학 학사, 서울대학교 (2011 - 2015). 기술 스택: Java, Python, JavaScript, AWS, Docker, Kubernetes. 자격증: AWS Certified Solutions Architect, PMP." motivation="IT 기술을 통해 세상을 더 나은 곳으로 만들고자 하는 열정. 지속적인 자기 개발을 통해 최신 기술 습득 및 적용. 팀과의 협업을 통해 혁신적인 솔루션을 창출하고자 하는 목표입니다."

http :8088/resumes userId:='{"id":2}' position="시스템 유지보수" career="Junior Developer at DEF Ltd (2020 - Present): 간단한 웹 페이지 유지보수. 팀 프로젝트 보조 역할 수행." qualifications="자격요건: 컴퓨터 공학 전공, 무명대학교 (2016 - 2020). 기술 스택: HTML, CSS, 기본적인 JavaScript." motivation="IT 분야에서의 경력을 쌓고 싶음. 새로운 기술을 배우고 싶음."

// 이력서 요약
http PUT :8088/resumes/1/summerizeaibasedresume
http PUT :8088/resumes/2/summerizeaibasedresume
