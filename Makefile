restart:
	docker compose down
	docker compose build backend
	docker compose up -d backend

logs:
	docker compose logs -f backend