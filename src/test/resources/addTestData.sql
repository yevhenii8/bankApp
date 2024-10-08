INSERT INTO managers (id,
                      ssn,
                      first_name,
                      last_name,
                      status,
                      created_at,
                      updated_at) VALUES ('6e5a9d7b-8c4f-4d1b-a3e9-5f2c7a8b1d3e',
                                          '429-40-2011',
                                          'John',
                                          'Doe',
                                          'ACTIVE',
                                          '2024-07-29T00:00:00',
                                          '2024-07-29T00:00:00');

INSERT INTO clients (id,
                     ssn,
                     manager_id,
                     status,
                     tax_code,
                     first_name,
                     last_name,
                     email,
                     address,
                     phone,
                     created_at,
                     updated_at) VALUES ('f95a4c9a-3a5b-4c22-8b56-73b2a3e831df',
                                         '123-45-6789',
                                         '6e5a9d7b-8c4f-4d1b-a3e9-5f2c7a8b1d3e',
                                         'ACTIVE',
                                         'TAX001',
                                         'John',
                                         'Doe',
                                         'john.doe@example.com',
                                         '123 Main St, City, Country',
                                         '+1234567890',
                                         '2024-07-29T00:00:00',
                                         '2024-07-29T00:00:00');

INSERT INTO accounts (id,
                      client_id,
                      username,
                      password,
                      type,
                      status,
                      balance,
                      currency,
                      created_at,
                      updated_at) VALUES ('12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0',
                                          'f95a4c9a-3a5b-4c22-8b56-73b2a3e831df',
                                          'Account 1',
                                          'R8jvP3wz!DqL',
                                          'ONLINE_BANKING',
                                          'ACTIVE',
                                          1000,
                                          'USD',
                                          '2024-07-29T00:00:00',
                                          '2024-07-29T00:00:00');

INSERT INTO products (id,
                      product_name,
                      status,
                      interest_rate,
                      product_limit,
                      created_at,
                      updated_at) VALUES ('b3f6d9e1-4c2a-4e8b-9a7e-6c8d5f3a1e9c',
                                          'SAVINGS_ACCOUNT',
                                          'ACTIVE',
                                          '5.0',
                                          '50000',
                                          '2024-07-29T00:00:00',
                                          '2024-07-29T00:00:00');

INSERT INTO agreements (id,
                        account_id,
                        product_id,
                        manager_id,
                        interest_rate,
                        status,
                        sum,
                        created_at,
                        updated_at) VALUES ('e7b9c46b-12d5-41eb-b0e4-bc5b77c44a56',
                                            '12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0',
                                            'b3f6d9e1-4c2a-4e8b-9a7e-6c8d5f3a1e9c',
                                            '6e5a9d7b-8c4f-4d1b-a3e9-5f2c7a8b1d3e',
                                            5.00,
                                            'ACTIVE',
                                            10000.00,
                                            '2024-07-29T00:00:00',
                                            '2024-07-29T00:00:00');

INSERT INTO transactions (id,
                          account_id,
                          transaction_type,
                          amount,
                          description,
                          created_at) VALUES ('b2e8c5a1-9f7d-4b6c-8a3e-5d1c2e7f9b4e',
                                              '12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0',
                                              'DEPOSIT',
                                              1000.00,
                                              'Initial deposit',
                                              '2024-07-29T00:00:00');