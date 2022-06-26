const app = getApp()
const subscribers = {}

export default function watch(wname, method) {
  if (!subscribers[wname]) {
    subscribers[wname] = []
  }
  subscribers[wname].push(method)
  const obj = app.globalData
  const source = obj[wname]
  if (source) {
    subscribers[wname].map(method => method(source))
  }
  Object.defineProperty(obj, wname, {
    configurable: true,
    enumerable: true,
    set: function (value) {
      this['_' + wname] = value
      subscribers[wname].map(method => method(value))
    },
    get: function () {
      const exist = typeof this['_' + wname] !== 'undefined'
      return (this['_' + wname] = exist ? this['_' + wname] : source)
    }
  })
}
