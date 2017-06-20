package com.qylyx.july.utils.cold.stool;

import java.lang.reflect.Array;

import org.apache.commons.lang3.StringUtils;

import com.qylyx.july.utils.cold.stool.exception.ColDesGroupException;
import com.qylyx.july.utils.universal.intf.turn.val.ITurnVal;

/**
 * 多组类描述字符串分割工具，将多种类型多组的数据集成到一串字符串中，目的是想更直观，更易维护
 * <pre>
 * 如：String it = "ID, id, 50, 名字, name, 100, 年龄, age, 30, 生日, birthday, 150"
 * 分割后：
 * 		String[] colNames = new String[]{"ID", "名字", "年龄", "生日"};
 * 		String[] colProperties = new String[]{"id", "name", "age", "birthday"}
 * 		int[] colSize = new int[]{50, 100, 30, 150}
 * </pre>
 * @author Qiaoxin.Hong
 *
 */
public class ColDesGroup {
	
	//************************ 外部设置 *****************************
	
	/**
	 * 所有类描述的字符串
	 */
	protected String groupStr;
	
	/**
	 * 每组的列描述的数量
	 */
	protected int groupItemCount;
	
	/**
	 * 分割符，默认","
	 */
	protected String segm = ",";
	
	/**
	 * 是否对每个类型描述进行去空处理，默认true
	 */
	protected boolean trim = true;
	
	
	//************************ 内部使用 *****************************
	
	/**
	 * 是否已初始化过
	 */
	protected boolean initFlag = false;
	
	/**
	 * 最原生的分割出来的所有类描述的数组
	 */
	protected String[] groupArr;
	
	/**
	 * 已解析成数组的类描述
	 */
	protected Object[] resolveArr;
	
	/**
	 * 最终能解析出多少组类描述，由总数量/groupItemCount得到
	 */
	protected int groupCount;
	
	/**
	 * 多组类描述字符串分割工具
	 */
	public ColDesGroup() {
		
	}
	
	/**
	 * 多组类描述字符串分割工具
	 * @param groupStr 所有类描述的字符串
	 * @param groupItemCount 每组的列描述的数量
	 */
	public ColDesGroup(String groupStr, int groupItemCount) {
		setGroupStr(groupStr);
		setGroupItemCount(groupItemCount);
	}
	
	/**
	 * 取得字符串数组类型的列
	 * @param colIndex 列的下标
	 * @return
	 */
	public String[] getArr(int colIndex) {
		String[] arr = (String[]) getBefore(colIndex);
		//未生成过则重新生成
		if (arr == null) {
			arr = getArr(colIndex, null);
		}
		return arr;
	}
	
	/**
	 * 取得某数组类型的列
	 * @param colIndex 列的下标
	 * @param turnVal 数据转换器
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R> R[] getArr(int colIndex, ITurnVal<String, R> turnVal) {
		//获取列之前的一些操作
		R[] arr = (R[]) getBefore(colIndex);
		//未生成过则重新生成
		if (arr == null) {
			int index = 0;
			for (int i = colIndex; i < groupArr.length; i+=groupItemCount) {
				//进行去空处理
				String colStr = trim ? groupArr[i].trim() : groupArr[i];
				R val = turnVal == null ? (R) colStr : turnVal.turn(colStr);
				if (arr == null)
					arr = (R[]) Array.newInstance(val.getClass(), groupCount);
				arr[index++] = val;
			}
			resolveArr[colIndex] = arr;
		}
		return arr;
	}
	
	//******************* 由于基础数据类型与泛型的使用有冲突，所以进行一些特殊处理 ***********************
	
	/**
	 * 取得int数组类型的列
	 * @param colIndex 列的下标
	 * @return
	 */
	public int[] getArrInt(int colIndex) {
		//获取列之前的一些操作
		int[] arr = (int[]) getBefore(colIndex);
		//未生成过则重新生成
		if (arr == null) {
			Integer[] itArr = getArr(colIndex, o -> {
				return Integer.valueOf(o);
			});
			if (itArr != null) {
				arr = new int[groupCount];
				for (int i = 0; i < itArr.length; i++) {
					arr[i] = itArr[i];
				}
			}
		}
		return arr;
	}
	
	/**
	 * 取得long数组类型的列
	 * @param colIndex 列的下标
	 * @return
	 */
	public long[] getArrLong(int colIndex) {
		//获取列之前的一些操作
		long[] arr = (long[]) getBefore(colIndex);
		//未生成过则重新生成
		if (arr == null) {
			Long[] itArr = getArr(colIndex, o -> {
				return Long.valueOf(o);
			});
			if (itArr != null) {
				arr = new long[groupCount];
				for (int i = 0; i < itArr.length; i++) {
					arr[i] = itArr[i];
				}
			}
		}
		return arr;
	}
	
	/**
	 * 取得double数组类型的列
	 * @param colIndex 列的下标
	 * @return
	 */
	public double[] getArrDouble(int colIndex) {
		//获取列之前的一些操作
		double[] arr = (double[]) getBefore(colIndex);
		//未生成过则重新生成
		if (arr == null) {
			Double[] itArr = getArr(colIndex, o -> {
				return Double.valueOf(o);
			});
			if (itArr != null) {
				arr = new double[groupCount];
				for (int i = 0; i < itArr.length; i++) {
					arr[i] = itArr[i];
				}
			}
		}
		return arr;
	}
	
	/**
	 * 取得float数组类型的列
	 * @param colIndex 列的下标
	 * @return
	 */
	public float[] getArrFloat(int colIndex) {
		//获取列之前的一些操作
		float[] arr = (float[]) getBefore(colIndex);
		//未生成过则重新生成
		if (arr == null) {
			Float[] itArr = getArr(colIndex, o -> {
				return Float.valueOf(o);
			});
			if (itArr != null) {
				arr = new float[groupCount];
				for (int i = 0; i < itArr.length; i++) {
					arr[i] = itArr[i];
				}
			}
		}
		return arr;
	}
	
	
	/**
	 * 获取列之前的一些操作
	 * @param colIndex
	 * @return
	 */
	private Object getBefore(int colIndex) {
		//初始化
		init();
		if (colIndex < 0 || colIndex > groupItemCount - 1)
			throw new ColDesGroupException(String.format("要检索的类下标超设定范围！范围：%s~%s", 0, groupItemCount - 1));
		//如之前已解析过来了，则直接返回
		if (resolveArr[colIndex] != null)
			return resolveArr[colIndex];
		return null;
	}
	
	/**
	 * 初始化，及一些相关验证
	 */
	private void init() {
		if (!initFlag) {
			groupArr = groupStr.split(segm);
			if (groupArr.length < groupItemCount)
				throw new ColDesGroupException("所有类描述的数量低于设置的每组的数量！");
			if (groupArr.length % groupItemCount != 0)
				throw new ColDesGroupException("设置的每组的列描述的数量与实际数据数量不匹配！");
			
			groupCount = groupArr.length / groupItemCount;
			resolveArr = new Object[groupItemCount];
			initFlag = true;
		}
	}
	
	
	public ColDesGroup setGroupStr(String groupStr) {
		if (StringUtils.isBlank(groupStr))
			throw new ColDesGroupException("所有类描述的字符串不能为空！");
		this.groupStr = groupStr;
		return this;
	}
	
	public String getGroupStr() {
		return groupStr;
	}
	
	public ColDesGroup setGroupItemCount(int groupItemCount) {
		if (groupItemCount < 1)
			throw new ColDesGroupException("每组的列描述的数量不能低于1！");
		this.groupItemCount = groupItemCount;
		return this;
	}
	
	public int getGroupItemCount() {
		return groupItemCount;
	}
	
	public ColDesGroup setSegm(String segm) {
		if (segm == null)
			throw new ColDesGroupException("分割符不能为null！");
		this.segm = segm;
		return this;
	}
	
	public String getSegm() {
		return segm;
	}
	
	public boolean isTrim() {
		return trim;
	}
	
	public ColDesGroup setTrim(boolean trim) {
		this.trim = trim;
		return this;
	}
}
