How to reproduce the bug:

1. Start the application (from IDE or maven)

    `mvn spring-boot:run`
    
2. Do curl request

    `curl localhost:8080/batchdownload/4 > o.zip`
    
3. Until end of this request abort it with Ctrl+C

4. Do curl request from step 2 again. In log you will see

    `CoyoteAdapter     : Encountered a non-recycled response and recycled it forcedly`
