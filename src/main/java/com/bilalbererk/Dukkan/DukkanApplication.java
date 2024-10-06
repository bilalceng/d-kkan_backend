package com.bilalbererk.Dukkan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;


@SpringBootApplication
public class DukkanApplication {

	public static void main(String[] args) {
		 ApplicationContext context = SpringApplication.run(DukkanApplication.class, args);
		 DataLoadingService service = (DataLoadingService) context.getBean("service");
		 service.loadData();

		 String[] beanNames = context.getBeanDefinitionNames();
		 for(String beanName:beanNames){
			 System.out.println(beanName);
		 }
	}

}

@Configuration
class AppConfig{

	@Bean
	@Primary
	public Supplier<DataSource> provideLocalDataSource(){
		return LocalDataSource::new;
	}

	@Bean
	public Supplier<DataSource> provideNetworkDataSource(){
		return NetworkDataSource::new;
	}

	@Bean(name = "service")
	public DataLoadingService provideDataLoaderService(
			Supplier<DataSource> dataSource
	){
		return new DataLoadingService(dataSource);
	}
}

interface DataSource {
	void getData();
}

class NetworkDataSource implements DataSource{

	@Override
	public void getData() {
		System.out.println("Loading data from network...");
	}
}

class LocalDataSource implements DataSource{

	@Override
	public void getData() {
		System.out.println("Loading data from Local...");
	}
}
@Service
class DataLoadingService{
	private Supplier<DataSource> dataSource;

	public DataLoadingService(Supplier<DataSource> dataSource){
		this.dataSource = dataSource;
	}

	public  void loadData(){
		dataSource.get().getData();
	}
}









