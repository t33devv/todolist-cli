# Load existing items
# 1. create a new item
# 2. list items
# 3. mark item as complete
# 4. save items
# 5. mark items as incomplete
# 6. delete items

import json

file_name = 'todo_list.json'

def load_tasks():
    try:
        with open(file_name, 'r') as file:
            return json.load(file)
    except:
        return {'tasks': []}

def save_tasks(tasks):
    try:
        with open(file_name, 'w') as file:
            json.dump(tasks, file)
    except:
        print('Failed to save.')

def view_tasks(tasks):
    task_list = tasks['tasks']
    if len(task_list) == 0:
        print('No tasks to display.')
    else:
        print('\nYour To-Do List: ')
        for idx, task in enumerate(task_list):
            status = '[Completed]' if task['complete'] else '[Pending]'
            print(f"{idx + 1}. {task['description']} | {status}")


def create_task(tasks):
    description = input('\nEnter the task description: ').strip()
    if description:
        tasks['tasks'].append({"description": description, 'complete': False})
        save_tasks(tasks)
        print('Task added.')
    else:
        print('Description cannot be empty.')

def mark_task_complete(tasks):
    view_tasks(tasks)
    try:
        task_number = int(input('\nEnter the task number to mark as complete: ').strip())
        if tasks['tasks'][task_number - 1]['complete']:
            print('Task already marked as complete.')
        elif 1 <= task_number <= len(tasks):
            tasks['tasks'][task_number - 1]['complete'] = True
            save_tasks(tasks)
            print(f'Task {task_number} marked as complete.')
        else:
            print('Invalid task number.')
    except:
        print('Enter a valid number.')

def mark_task_incomplete(tasks):
    view_tasks(tasks)
    try:
        task_number = int(input('\nEnter the task you want to mark as incomplete: ').strip())
        if tasks['tasks'][task_number - 1]['complete'] == False:
            print('\nTask already marked as incomplete.')
        elif 1 <= task_number <= len(tasks):
            tasks['tasks'][task_number - 1]['complete'] = False
            save_tasks(tasks)
            print(f'Task {task_number} marked as incomplete.')
    except:
        print('Enter a valid number.')

def delete_task(tasks):
    view_tasks(tasks)
    try:
        task_number = int(input('\nEnter the task you want to delete: ').strip())
        if 1 <= task_number <= len(tasks):
            tasks['tasks'].pop(task_number - 1)
            save_tasks(tasks)
            print(f'Task deleted successfully.')
    except:
        print('Enter valid number.')

def main():
    tasks = load_tasks()

    while True:
        print("\nTo-Do List Manager")
        print('1. View Tasks')
        print('2. Add Task')
        print('3. Complete Task')
        print('4. Mark Task as Pending')
        print('5. Delete task')
        print('6. Exit')

        choice = input('Enter your choice: ').strip()

        if choice == '1':
            view_tasks(tasks)
        elif choice == '2':
            create_task(tasks)
        elif choice == '3':
            mark_task_complete(tasks)
        elif choice == '4':
            mark_task_incomplete(tasks)
        elif choice == '5':
            delete_task(tasks)
        elif choice =='6':
            print('Goodbye!')
            break
        else:
            print('Invalid choice, please try again.')


if __name__ == "__main__":
    main()
