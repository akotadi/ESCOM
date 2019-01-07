//Pal jueves 12-Oct-2018
#include <stdio.h>
#include <stdlib.h>

int main(void){
	int id_proc, i,j,k,l,m,p;
	for(i=0;i<4;i++)
	{
		id_proc=fork();//1
		if(id_proc==0){
			printf("\nSoy un proceso hijo")
			if(i==3){
				for(j=0;j<4;j++){
					id_proc=fork();//2
					if(id_proc==0){
						if(j==0){//2.1
							id_proc=fork();
							if(id_proc==0){
								for(k=0;k<3;k++){
									id_proc=fork();
									if(id_proc==0){
										printf("\nSoy un proceso hijo");
										if(k==0){//2.1.1
											id_proc=fork();
											if(id_proc==0){
												for(l=0;l<3;l++){
													id_proc=fork();
													if(id_proc==0){
														printf("\nSoy un proceso hijo");
														if(l==0){//2.1.1.1
															id_proc=fork();
															if(id_proc==0){
																printf("\nSoy un proceso hijo");
																exit(0);
															}
				
														}
														else if(l==1){//2.1.1.2
															printf("\nSoy un proceso hijo");
															for(m=0,m<2;m++){
																id_proc=fork();
																if(id_proc==0){
																	printf("\nSoy un proceso hijo");
																    //exit(0);
																}
																else{
																	break;
																}
															}
															exit(0);
														}
														else if(l==2){//2.1.1.3
															printf("\nSoy un proceso hijo");
															for(m=0,m<3;m++){
																id_proc=fork();
																if(id_proc==0){
																	printf("\nSoy un proceso hijo");
																    exit(0);
																}
																else{
																	printf("\nSoy un proceso padre");
																	 break;
																}
															}
															exit(0);
														}
														exit(0);
													}
													/*else{
														printf("\nSoy un proceso padre");
														//exit(0);
													}*/
												}
											}
											/*else{
												printf("\nSoy un proceso padre");
												//exit(0);
											}*/
										}
										else if(k==1){//2.1.2
												printf("\nSoy un proceso hijo");
												exit(0);
										}
										else if(k==2){//2.1.3
											id_proc=fork();
											if(id_proc==0){
												printf("\nSoy un proceso hijo");
												for(l=0;l<2;l++){
													id_proc=fork();
													if(id_proc==0){
														if(l==0){//2.1.3.1
															printf("\nSoy un proceso hijo");
															exit(0);
														}
														else if(l==1){//2.1.3.2
															printf("\nSoy un proceso hijo");
															for(m=0;m<2;m++){
																id_proc=fork();
																if(id_proc==0){
																	printf("\nSoy un proceso hijo");
																	exit(0);
																}
																/*else{
																	printf("\nSoy un proceso padre");
																}*/
															}
															exit(0);
														}
													}
													/*else{
														printf("\nSoy un proceso padre");
														//exit(0);
													}*/
												}
												exit(0);
											}
											exit(0);
											/*else{
												printf("\nSoy un proceso padre");
												//exit(0);
											}*/
										}
									}
									/*else{
										printf("\nSoy un proceso padre");
										//exit(0);
									}*/
								}
							}
							/*else{
								printf("\nSoy un proceso padre");
								//exit(0);
							}*/
						}
						else if(j==1){//2.2
							printf("\nSoy un proceso hijo");
							exit(0);
						}
						else if(j==2){//2.3
							id_proc=fork();
							if(id_proc==0){
								printf("\nSoy un proceso hijo");
								for(k=0;k<2;k++){
									id_proc=fork();
									if(id_proc==0){
										if(k==0){//2.3.1
											printf("\nSoy un proceso hijo");
											exit(0);
										}
										else if(k==1){//2.3.2
											id_proc=fork();
											if(id_proc==0){
												printf("\nSoy un proceso hijo");
												for(l=0;l<3;l++){
													id_proc=fork();
													if(id_proc==0){
														printf("\nSoy un proceso hijo");
														if(l==0){//2.3.2.1
															id_proc=fork();
															if(id_proc==0){
																printf("\nSoy un proceso hijo");
																for(m=0;m<2;m++){
																	id_proc=fork();
																	if(id_proc==0){
																		printf("\nSoy un proceso hijo");
																		exit(0);
																	}
																	/*else{
																		printf("\nSoy un proceso padre");
																	}*/
																}
															}
															exit(0);
															/*else{
																printf("\nSoy un proceso padre");
																//exit(0);
															}*/

														}
														else if(l==1){//2.3.2.2
															id_proc=fork();
															if(id_proc==0){
																printf("\nSoy un proceso hijo");
																exit(0);
															}
															/*else{
																printf("\nSoy un proceso padre");
																//exit(0);
															}*/

														}
														else if(l==2){//2.3.2.3
															id_proc=fork();
															if(id_proc==0){
																printf("\nSoy un proceso hijo");
																for(m=0;m<3;m++){
																	id_proc=fork();
																	if(id_proc==0){
																		printf("\nSoy un proceso hijo");
																		exit(0);
																	}
																	/*else{
																		printf("\nSoy un proceso padre");
																		//exit(0);
																	}*/
																}
															}
															exit(0);
															/*else{
																printf("\nSoy un proceso padre");
																//exit(0);
															}*/
														}
													}
													/*else{
														printf("\nSoy un proceso padre");
														//exit(0);
													}*/
												}
											}
											exit(0);
											/*else{
												printf("\nSoy un proceso padre");
												//exit(0);
											}*/

										}
									}
									exit(0);
									/*else{
										printf("\nSoy un proceso padre");
										//exit(0);
									}*/
								}
							}
							exit(0);
							/*else{
								printf("\nSoy un proceso padre");
								//exit(0);
							}*/
						}
						else if(j==3){//2.4
							for(p=0;p<3;p++){
								id_proc=fork();
								if(id_proc==0){
									if(p<2){//2.4.1 y 2.4.2
										printf("\nSoy un proceso hijo");
										exit(0);
									}
									if(p==2){//2.4.3
										printf("\nSoy un proceso hijo");
										for(k=0;k<3;k++){
											id_proc=fork();
											if(id_proc==0){
												printf("\nSoy un proceso hijo");
												if(k==3){
													for(l=0;l<4;l++){
														id_proc=fork();
														if(id_proc==0){
															if(l==0 || l==2){//2.4.3.1 y 2.4.3.3
																printf("\nSoy un proceso hijo");
																exit(0);
															}
															else if(l==1 || l==3){//2.4.3.2 y 2.4.3.4
																printf("\nSoy un proceso hijo");
																for(m=0;m<2;m++){
																	id_proc=fork();
																	if(id_proc==0){
																		printf("\nSoy un proceso hijo");
																		exit(0);
																	}
																	/*else{
																		printf("\nSoy un proceso padre");
																		//exit(0);
																	}*/
																}
																exit(0);
															}
														}
														exit(0);
														/*else{
															printf("\nSoy un proceso padre");
															//exit(0);
														}*/
													}

												}
											}
											exit(0);
											/*else{
												printf("\nSoy un proceso padre");
												break;
											}*/
										}
										
									}
								}
								exit(0);
								/*else{
									printf("\nSoy un proceso padre");
									//exit(0);
								} */
							}

						}
					}
					exit(0);
					/*else{
						printf("\nSoy un proceso padre");
						//exit(0);
					}*/
				}
			}
		}
		else{
			break;
			exit(0);
		}
	}
	exit(0);
}