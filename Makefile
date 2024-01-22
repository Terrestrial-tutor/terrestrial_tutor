restart:
	docker compose down backend --remove-orphans
	docker compose build backend
	docker compose up -d backend

logs:
	docker compose logs -f backend