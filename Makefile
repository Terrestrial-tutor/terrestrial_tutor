restart:
	docker stop terrestrial_tutor-backend-1
	docker rm terrestrial_tutor-backend-1
	docker compose build backend
	docker compose up -d

start:
	docker compose build backend
	docker compose up -d

logs:
	docker compose logs -f backend

server_restart_backend_git:
	git pull
	docker compose build backend
	docker stop terrestrial_tutor-backend-1
	docker rm terrestrial_tutor-backend-1
	docker compose up -d backend