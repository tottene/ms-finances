ALTER TABLE expenses ADD COLUMN category_id UUID NOT NULL;
ALTER TABLE expenses ADD CONSTRAINT fk_expense_category FOREIGN KEY (category_id) REFERENCES categories(id);
