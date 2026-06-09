import json
import os
from datetime import datetime

# Arquivo para armazenar usuários
USUARIOS_FILE = "usuarios.json"

def carregar_usuarios():
    """Carrega usuários do arquivo JSON"""
    if os.path.exists(USUARIOS_FILE):
        with open(USUARIOS_FILE, 'r', encoding='utf-8') as f:
            return json.load(f)
    return {}

def salvar_usuarios(usuarios):
    """Salva usuários no arquivo JSON"""
    with open(USUARIOS_FILE, 'w', encoding='utf-8') as f:
        json.dump(usuarios, f, indent=4, ensure_ascii=False)

def cadastro():
    """Função para cadastrar um novo usuário"""
    print("\n" + "="*50)
    print("CADASTRO DE NOVO USUÁRIO")
    print("="*50)
    
    usuarios = carregar_usuarios()
    
    nome = input("Nome: ").strip()
    email = input("Email: ").strip()
    senha = input("Senha: ").strip()
    
    if email in usuarios:
        print("❌ Este email já está cadastrado!")
        return
    
    usuarios[email] = {
        "nome": nome,
        "senha": senha,
        "data_cadastro": datetime.now().strftime("%d/%m/%Y %H:%M")
    }
    
    salvar_usuarios(usuarios)
    print("✅ Cadastro realizado com sucesso!")

def login():
    """Função para fazer login"""
    print("\n" + "="*50)
    print("LOGIN")
    print("="*50)
    
    usuarios = carregar_usuarios()
    
    if not usuarios:
        print("❌ Nenhum usuário cadastrado!")
        return False
    
    email = input("Email: ").strip()
    senha = input("Senha: ").strip()
    
    if email in usuarios and usuarios[email]["senha"] == senha:
        print(f"✅ Bem-vindo, {usuarios[email]['nome']}!")
        return True
    else:
        print("❌ Email ou senha incorretos!")
        return False

def menu():
    """Menu principal"""
    while True:
        print("\n" + "="*50)
        print("SISTEMA DE CADASTRO E LOGIN")
        print("="*50)
        print("1. Cadastrar")
        print("2. Login")
        print("3. Sair")
        print("="*50)
        
        opcao = input("Escolha uma opção: ").strip()
        
        if opcao == "1":
            cadastro()
        elif opcao == "2":
            login()
        elif opcao == "3":
            print("👋 Até logo!")
            break
        else:
            print("❌ Opção inválida!")

if __name__ == "__main__":
    menu()
