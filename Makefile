restart:
	docker compose down
	docker compose build backend
	docker compose up -d backend

logs:
	docker compose logs -f backend

server_restart_backend:
	docker stop terrestrial_tutor-backend-1
	docker rm terrestrial_tutor-backend-1
	docker compose up -d backend