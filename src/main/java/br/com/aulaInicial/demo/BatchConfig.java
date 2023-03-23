package br.com.aulaInicial.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

	/*
	 * TAREFA = JOB 
	 * Criando o metodo que retorna o Job e que configura como esse Job será criado
	 * 
	 * @jobRepositoy - Orquestra o fluxo de execução 
	 * @step - São varias etapas 
	 * @build - Criar instancia de Jobs
	 * @tasklet - Uma step mais simples, para uma tarefa simples, 
	 * executa e encerra, recebe uma interface
	 * @transactionManager - Gerenciador de transação
	 */

	@Bean
	public Job job(JobRepository jobRepositoy, Step step) {
		return new JobBuilder("job", jobRepositoy).start(step).build();
	}

	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step", jobRepository)
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("Ola, Mundo");
					return RepeatStatus.FINISHED;
				}, transactionManager).build();
	}
}
