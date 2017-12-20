export class User {
  private _id: number;
  private _email: string;
  private _firstName: string;
  private _lastName: string;
  private _totalPoints: number;
  private _role: string;
  private _enrollments: Array<string>[];

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get lastName(): string {
    return this._lastName;
  }

  set lastName(value: string) {
    this._lastName = value;
  }

  get totalPoints(): number {
    return this._totalPoints;
  }

  set totalPoints(value: number) {
    this._totalPoints = value;
  }

  get role(): string {
    return this._role;
  }

  set role(value: string) {
    this._role = value;
  }

  get enrollments(): Array<string>[] {
    return this._enrollments;
  }

  set enrollments(value: Array<string>[]) {
    this._enrollments = value;
  }
}
