restart_backend:
	docker compose build backend
	docker stop terrestrial_tutor-backend-1
	docker rm terrestrial_tutor-backend-1
	docker compose up -d backend
	docker compose ps

restart_frontend:
	docker compose down nginx frontend
	docker compose up -d nginx
	docker compose ps

start:
	docker compose build backend
	docker compose up -d

logs_backend:
	docker compose logs -f backend

logs_frontend:
	docker compose logs -f frontend