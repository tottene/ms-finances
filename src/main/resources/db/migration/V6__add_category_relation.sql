ALTER TABLE incomes ADD COLUMN category_id UUID NOT NULL;
ALTER TABLE incomes ADD CONSTRAINT fk_income_category FOREIGN KEY (category_id) REFERENCES categories(id);
